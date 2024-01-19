package il.ac.afeka.usersservice;

import il.ac.afeka.usersservice.boundaries.UserBoundary;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveUsersService implements UsersService {


	@Override
	public Flux<UserBoundary> getUsersbyDomain(String domain) {
		return null;
	}

	@Override
	public Flux<UserBoundary> getUsersbyMinimumAge(String minimumAgeInYears) {
		return null;
	}

	@Override
	public Flux<UserBoundary> getUsersbyLastname(String prefix) {
		return null;
	}

	@Override
	public Mono<Void> deleteAll() {
		return null;
	}

	@Override
	public Flux<UserBoundary> getAll() {
		return null;
	}
}
