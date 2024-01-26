package il.ac.afeka.usersservice.controllers;

import il.ac.afeka.usersservice.boundaries.*;
import il.ac.afeka.usersservice.logic.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping(path = {"/users"})
public class ReactiveUsersController {
	private UsersService usersService;

	public ReactiveUsersController(UsersService usersService) {
		this.usersService = usersService;
	}

	// Triggered when no parameters are provided
	@GetMapping(produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
	public Flux<UserBoundary> getAllUsers() {
		return this.usersService.getAll().log();
	}

	@PostMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public Mono<UserBoundary> createUser(@RequestBody NewUserBoundary user) {
		return this.usersService.createUser(user).log();
	}

	// New distinct mapping for getUserByEmailAndPassword
	@GetMapping(path = "/{email}", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
	public Mono<UserBoundary> getUserByEmailAndPassword(
			@PathVariable("email") String email,
			@RequestParam("password") String password) {
		return this.usersService.GetUserByEmail(email, password).log();
	}

	// Triggered when 'criteria' and 'value' parameters are provided
	@GetMapping(params = {"criteria", "value"}, produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
	public Flux<UserBoundary> getUsers(
			@RequestParam(required = false) String criteria,
			@RequestParam(required = false) String value) {
		return getUsersByCriteria(criteria, value);
	}

	private Flux<UserBoundary> getUsersByCriteria(String criteria, String value) {
		return switch (criteria) {
			case "byLastname" -> this.usersService.getUsersByLastname(value).log();
			case "byMinimumAge" -> this.usersService.getUsersByMinimumAge(Integer.parseInt(value)).log();
			case "byDomain" -> this.usersService.getUsersByDomain(value).log();
			case "byDepartmentId" -> this.usersService.getUserByDepartmentId(value).log();
			default -> Flux.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Criteria not found"));
		};
	}

	@PutMapping(path = "/{email}/department", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<UserBoundary> linkUserToDepartment(
			@PathVariable("email") String email,
			@RequestBody DepartmentIdBoundary departmentId) {

		return this.usersService
				.linkUserToDepartment(email,departmentId)
				.log();
	}

	@DeleteMapping
	public Mono<Void> deleteAll() {
		return this.usersService.deleteAll().log();
	}
}
