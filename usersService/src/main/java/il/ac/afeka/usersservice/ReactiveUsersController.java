package il.ac.afeka.usersservice;

import il.ac.afeka.usersservice.UsersService;
import il.ac.afeka.usersservice.boundaries.UserBoundary;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = {"/users"})
public class ReactiveUsersController {
	private UsersService usersService;
	
	public ReactiveUsersController(UsersService usersService) {
		this.usersService = usersService;
	}
	
	@GetMapping(
			produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
	public Flux<UserBoundary> getAllUsers (){
		return this.usersService
			.getAll()
			.log();
	}
	@PostMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public Mono<UserBoundary> createMessage (
			@RequestBody UserBoundary user) {
		return this.usersService
				.createUser(user)
				.log();
	}
	public Mono<UserBoundary> getUserByEmailAndPassword(
			@PathVariable("email") String email,
			@RequestParam(name = "password") String password) {

		return this.usersService.GetUserByEmail(email, password)
				.log();
	}
	@GetMapping(
			produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
	public Flux<UserBoundary> getUsers(
				@PathVariable(name = "criteria") String criteria,
				@PathVariable(name = "value") String value) {
		return getUsersByCitria(criteria, value);
	}

	private Flux<UserBoundary> getUsersByCitria(String criteria, String value) {
		switch (criteria) {
			case "byLastname":
				return this.usersService.getUsersbyLastname(value)
						.log();
			case "byMinimumAge":
				return this.usersService.getUsersbyMinimumAge(value)
						.log();
			case "byDomain":
				return this.usersService.getUsersbyDomain(value)
						.log();
			default:
				return Flux.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Criteria not found"));
			}
	}

	@DeleteMapping
	public Mono<Void> deleteAll(){

		return this.usersService
			.deleteAll()
			.log();
	}
}
