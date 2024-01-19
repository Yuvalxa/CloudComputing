
import il.ac.afeka.usersservice.UsersService;
import il.ac.afeka.usersservice.boundaries.UserBoundary;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = {"/messages"})
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

	@GetMapping(
			path = "/users",
			produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
	public Flux<UserBoundary> getUsers(
				@PathVariable(name = "criteria") String criteria,
				@PathVariable(name = "value") String value) {
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
			default: // todo return empty Mono
			return this.usersService.getUsersbyDomain(value)
					.log();
		}
	}
	@DeleteMapping
	public Mono<Void> deleteAll(){
		return this.usersService
			.deleteAll()
			.log();
	}
}
