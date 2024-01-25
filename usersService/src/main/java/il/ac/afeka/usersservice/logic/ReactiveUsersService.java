package il.ac.afeka.usersservice.logic;

import il.ac.afeka.usersservice.boundaries.DepartmentBoundary;
import il.ac.afeka.usersservice.boundaries.NewUserBoundary;
import il.ac.afeka.usersservice.boundaries.UserBoundary;
import il.ac.afeka.usersservice.data.UserEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Arrays;
import java.util.stream.Stream;

@Service
public class ReactiveUsersService implements UsersService {
    private ReactiveUserCrud userCrud;

    public ReactiveUsersService(ReactiveUserCrud userCrud) {
        this.userCrud = userCrud;
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
    public Flux<UserBoundary> getUserByDepartmentIdAndValue(String departmentId) {
        return null;
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
    public Mono<UserBoundary> linkUserToDepartment(String email, DepartmentBoundary department) {
        Mono<UserEntity> userMono = this.userCrud.findByEmail(email);
        String departmentId = department.getDeptId();

        return userMono.flatMap(user -> {
                    String[] updatedRoles = Stream.concat(Arrays.stream(user.getRoles()), Stream.of(departmentId))
                            .toArray(String[]::new);
                    user.setRoles(updatedRoles);
                    return this.userCrud.save(user);
                })
                .map(UserBoundary::new);
    }
}
