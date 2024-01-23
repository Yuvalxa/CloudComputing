package il.ac.afeka.usersservice.logic;

import il.ac.afeka.usersservice.boundaries.DepartmentBoundary;
import il.ac.afeka.usersservice.boundaries.UserBoundary;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class ReactiveUsersService implements UsersService {

	@Override
	public Flux<UserBoundary> getUsersByDomain(String domain) {
		return null;
	}
	@Override
	public Flux<UserBoundary> getUsersByMinimumAge(String minimumAgeInYears) {
		return null;
	}
	@Override
	public Flux<UserBoundary> getUsersByLastname(String prefix) {
		return null;
	}
	@Override
	public Flux<UserBoundary> getUserByDepartmentIdAndValue(String departmentId) {return null;}
	@Override
	public Mono<Void> deleteAll() {
		return null;
	}
	@Override
	public Flux<UserBoundary> getAll() {
		return null;
	}

	@Override
	public Mono<UserBoundary> GetUserByEmail(String email, String password) {
		return null;
	}

	@Override
	public Mono<UserBoundary> createUser(UserBoundary user) {
		return null;
	}

	@Override
	public Mono<UserBoundary> linkUserToDepartment(String email, DepartmentBoundary departmentBoundaryJson) {
		return null;
	}
}
