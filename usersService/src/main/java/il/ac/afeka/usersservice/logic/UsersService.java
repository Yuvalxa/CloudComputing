package il.ac.afeka.usersservice.logic;

import il.ac.afeka.usersservice.boundaries.NewUserBoundary;
import il.ac.afeka.usersservice.boundaries.UserBoundary;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsersService {
	public Flux<UserBoundary> getUsersByDomain(String domain);
	public Flux<UserBoundary> getUsersByMinimumAge(int minimumAgeInYears);
	public Flux<UserBoundary> getUsersByLastname(String lastName);
	public Flux<UserBoundary> getUserByDepartmentIdAndValue(String departmentId);
	public Mono<Void> deleteAll();
	public Flux<UserBoundary>  getAll();
	public Mono<UserBoundary> GetUserByEmail(String email, String password);
	public Mono<UserBoundary> createUser(UserBoundary user);


}
