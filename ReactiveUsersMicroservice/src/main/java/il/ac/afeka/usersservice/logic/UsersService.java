package il.ac.afeka.usersservice.logic;

import il.ac.afeka.usersservice.boundaries.department.DepartmentWrapperBoundary;
import il.ac.afeka.usersservice.boundaries.user.NewUserBoundary;
import il.ac.afeka.usersservice.boundaries.user.UserBoundary;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsersService {
    public Flux<UserBoundary> getUsersByDomain(String domain);

    public Flux<UserBoundary> getUsersByMinimumAge(int minimumAgeInYears);

    public Flux<UserBoundary> getUsersByLastname(String lastName);

    public Flux<UserBoundary> getUserByDepartmentId(String departmentId);

    public Mono<Void> deleteAll();

    public Flux<UserBoundary> getAll();

    public Mono<UserBoundary> GetUserByEmail(String email, String password);

    public Mono<UserBoundary> createUser(NewUserBoundary user);

    public Mono<UserBoundary> linkUserToDepartment(String email, DepartmentWrapperBoundary department);
}
