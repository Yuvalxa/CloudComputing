package il.ac.afeka.usersservice.logic;

import il.ac.afeka.usersservice.boundaries.DepartmentWrapperBoundary;
import il.ac.afeka.usersservice.boundaries.NewUserBoundary;
import il.ac.afeka.usersservice.boundaries.UserBoundary;
import il.ac.afeka.usersservice.data.DepartmentEntity;
import il.ac.afeka.usersservice.data.UserEntity;
import il.ac.afeka.usersservice.util.PasswordManager;
import il.ac.afeka.usersservice.util.exceptions.AlreadyExistsException;
import il.ac.afeka.usersservice.util.exceptions.InvalidInputException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveUsersService implements UsersService {
    private final ReactiveUserCrud userCrud;
    private final ReactiveDepartmentCrud departmentCrud;

    public ReactiveUsersService(ReactiveUserCrud userCrud, ReactiveDepartmentCrud departmentCrud) {
        this.userCrud = userCrud;
        this.departmentCrud = departmentCrud;
    }

    @Override
    public Flux<UserBoundary> getUsersByDomain(String domain) {
        return this.userCrud
                .findByEmailEndingWith("@" + domain)
                .map(UserBoundary::new);
    }

    @Override
    public Flux<UserBoundary> getUsersByMinimumAge(int minimumAgeInYears) {
        return this.userCrud
                .findAll()
                .filter(user -> user.calculateAge() >= minimumAgeInYears)
                .map(UserBoundary::new);
    }

    @Override
    public Flux<UserBoundary> getUserByDepartmentId(String departmentId) {
        return this.userCrud
                .findAll()
                .filter(user -> user.getRoles().contains(departmentId))
                .map(UserBoundary::new);
    }

    @Override
    public Flux<UserBoundary> getUsersByLastname(String lastname) {
        return userCrud
                .findAllByLastname(lastname)
                .map(UserBoundary::new);
    }

    @Override
    public Mono<Void> deleteAll() {
        return this.userCrud.deleteAll();
    }

    @Override
    public Flux<UserBoundary> getAll() {
        return userCrud
                .findAll()
                .map(UserBoundary::new);
    }

    @Override
    public Mono<UserBoundary> GetUserByEmail(String email, String password) {
        return userCrud
                .findById(email)
                .filter(user -> PasswordManager.verify(password, user.getPassword()))
                .map(UserBoundary::new);
    }

    @Override
    public Mono<UserBoundary> createUser(NewUserBoundary user) {
        return userCrud.findById(user.getEmail())
                .flatMap(existingUser ->
                        // if user already exist -> throw an error
                        Mono.<UserBoundary>error(new AlreadyExistsException("User already exists")))
                .switchIfEmpty(
                        // if user does not exist -> check if all provided roles are valid
                        Flux.fromArray(user.getRoles())
                                .flatMap(this.departmentCrud::existsByDepartmentNameIgnoreCase)
                                .all(exists -> exists)
                                .flatMap(allValid -> {
                                    if (!allValid)
                                        return Mono.error(new InvalidInputException("One or more invalid roles"));
                                    else
                                        // create and return new user if all roles are valid
                                        return this.userCrud.save(user.toEntity()).map(UserBoundary::new);
                                })
                );
    }

    @Override
    public Mono<UserBoundary> linkUserToDepartment(String email, DepartmentWrapperBoundary department) {
        Mono<UserEntity> userMono = this.userCrud.findById(email);
        Mono<DepartmentEntity> departmentMono = this.departmentCrud.findById(department.getDepartment().getDeptId());

        return userMono.flatMap(user ->
                departmentMono.flatMap(departmentEntity -> {
                            user.addRole(departmentEntity.getDepartmentName());
                            return this.userCrud.save(user);
                        })
                        .map(UserBoundary::new)
        );
    }

}
