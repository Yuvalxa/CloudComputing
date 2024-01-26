package il.ac.afeka.usersservice.logic;

import il.ac.afeka.usersservice.boundaries.*;
import il.ac.afeka.usersservice.data.DepartmentEntity;
import il.ac.afeka.usersservice.data.UserEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class ReactiveUsersService implements UsersService {
    private ReactiveUserCrud userCrud;
    private ReactiveDepartmentCrud departmentCrud;

    public ReactiveUsersService(ReactiveUserCrud userCrud, ReactiveDepartmentCrud departmentCrud) {
        this.userCrud = userCrud;
        this.departmentCrud = departmentCrud;
    }

    @Override
    public Flux<UserBoundary> getUsersByDomain(String domain) {
        return this.userCrud
                .findByEmailEndingWith(domain)
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
                .filter(user -> Arrays.asList(user.getRoles()).contains(departmentId))
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
                .findByEmailAndPassword(email, password)
                .map(UserBoundary::new);
    }

    @Override
    public Mono<UserBoundary> createUser(NewUserBoundary user) {
        return Mono.just(user)
                .map(NewUserBoundary::toEntity)
                .flatMap(this.userCrud::save)
                .map(UserBoundary::new);
    }

    @Override
    public Mono<UserBoundary> linkUserToDepartment(String email, DepartmentWrapperBoundary department) {
        Mono<UserEntity> userMono = this.userCrud.findById(email);
        Mono<DepartmentEntity> departmentMono = this.departmentCrud.findById(department.getDepartment().getDeptId());

        return userMono.flatMap(user ->
                departmentMono.flatMap(departmentEntity -> {
                            Set<String> rolesSet = new HashSet<>(Arrays.asList(user.getRoles())); // make it set to neglect duplicate
                            rolesSet.add(departmentEntity.getDeptId()); //add new depID
                            String[] updatedRoles = rolesSet.toArray(new String[0]); //make it string array
                            user.setRoles(updatedRoles);
                            return this.userCrud.save(user);
                        })
                        .map(savedUser -> new UserBoundary(savedUser))
        );
    }

}
