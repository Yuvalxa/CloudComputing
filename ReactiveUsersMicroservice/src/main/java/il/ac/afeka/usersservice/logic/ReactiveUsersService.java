package il.ac.afeka.usersservice.logic;

import il.ac.afeka.usersservice.boundaries.department.DepartmentWrapperBoundary;
import il.ac.afeka.usersservice.boundaries.user.NewUserBoundary;
import il.ac.afeka.usersservice.boundaries.user.UserBoundary;
import il.ac.afeka.usersservice.data.DepartmentEntity;
import il.ac.afeka.usersservice.data.UserEntity;
import il.ac.afeka.usersservice.repositories.ReactiveDepartmentRepository;
import il.ac.afeka.usersservice.repositories.ReactiveUserRepository;
import il.ac.afeka.usersservice.util.PasswordManager;
import il.ac.afeka.usersservice.util.exceptions.AlreadyExistsException;
import il.ac.afeka.usersservice.util.exceptions.InvalidInputException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveUsersService implements UsersService {
    private final ReactiveUserRepository userRepository;
    private final ReactiveDepartmentRepository departmentRepository;

    public ReactiveUsersService(ReactiveUserRepository userRepository,
                                ReactiveDepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Flux<UserBoundary> getUsersByDomain(String domain) {
        return this.userRepository
                .findByEmailEndingWith("@" + domain)
                .map(UserBoundary::new);
    }

    @Override
    public Flux<UserBoundary> getUsersByMinimumAge(int minimumAgeInYears) {
        return this.userRepository
                .findAll()
                .filter(user -> user.calculateAge() >= minimumAgeInYears)
                .map(UserBoundary::new);
    }

    @Override
    public Flux<UserBoundary> getUserByDepartmentId(String departmentId) {
        return this.userRepository
                .findAll()
                .filter(user -> user.getRoles().contains(departmentId))
                .map(UserBoundary::new);
    }

    @Override
    public Flux<UserBoundary> getUsersByLastname(String lastname) {
        return userRepository
                .findAllByLastname(lastname)
                .map(UserBoundary::new);
    }

    @Override
    public Mono<Void> deleteAll() {
        return this.userRepository.deleteAll();
    }

    @Override
    public Flux<UserBoundary> getAll() {
        return userRepository
                .findAll()
                .map(UserBoundary::new);
    }

    @Override
    public Mono<UserBoundary> GetUserByEmail(String email, String password) {
        return userRepository
                .findById(email)
                .filter(user -> PasswordManager.verify(password, user.getPassword()))
                .map(UserBoundary::new);
    }

    @Override
    public Mono<UserBoundary> createUser(NewUserBoundary user) {
        return userRepository.findById(user.getEmail())
                .flatMap(existingUser ->
                        // if user already exist -> throw an error
                        Mono.<UserBoundary>error(new AlreadyExistsException("User already exists")))
                .switchIfEmpty(
                        // if user does not exist -> check if all provided roles are valid
                        Flux.fromArray(user.getRoles())
                                .flatMap(this.departmentRepository::existsByDepartmentNameIgnoreCase)
                                .all(exists -> exists)
                                .flatMap(allValid -> {
                                    if (!allValid)
                                        return Mono.error(new InvalidInputException("One or more invalid roles"));
                                    else
                                        // create and return new user if all roles are valid
                                        return this.userRepository.save(user.toEntity()).map(UserBoundary::new);
                                })
                );
    }

    @Override
    public Mono<UserBoundary> linkUserToDepartment(String email, DepartmentWrapperBoundary department) {
        Mono<UserEntity> userMono = this.userRepository.findById(email);
        Mono<DepartmentEntity> departmentMono = this.departmentRepository.findById(department.getDepartment().getDeptId());

        return userMono.flatMap(user ->
                departmentMono.flatMap(departmentEntity -> {
                            user.addRole(departmentEntity.getDepartmentName());
                            return this.userRepository.save(user);
                        })
                        .map(UserBoundary::new)
        );
    }

}
