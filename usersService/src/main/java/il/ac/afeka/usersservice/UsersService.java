package il.ac.afeka.usersservice;

import il.ac.afeka.usersservice.boundaries.UserBoundary;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsersService {

	public Flux<UserBoundary> getUsersbyDomain(String domain);
	public Flux<UserBoundary> getUsersbyMinimumAge(String minimumAgeInYears);
	public Flux<UserBoundary> getUsersbyLastname(String lastName);
	public Mono<Void> deleteAll();

	public Flux<UserBoundary>  getAll();
}
