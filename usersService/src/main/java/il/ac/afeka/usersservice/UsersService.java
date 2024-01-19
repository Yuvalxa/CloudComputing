package il.ac.afeka.usersservice;

import il.ac.afeka.usersservice.boundaries.UserBoundary;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsersService {

	public Flux<UserBoundary> getUsersbyDomain(String domain);
	public Flux<UserBoundary> getUsersbyMinimumAge(String minimumAgeInYears);
	public Flux<UserBoundary> getUsersbyLastname(String lastName);
	public Mono<Void> deleteAll();

	public Flux<UserBoundary>  getAll();

	public Mono<UserBoundary> GetUserByEmail(String email, String password);

	public Mono<UserBoundary> createUser(UserBoundary user);
}
