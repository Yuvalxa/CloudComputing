package il.ac.afeka.usersservice.controllers;

import il.ac.afeka.usersservice.boundaries.DepartmentWrapperBoundary;
import il.ac.afeka.usersservice.boundaries.NewUserBoundary;
import il.ac.afeka.usersservice.boundaries.UserBoundary;
import il.ac.afeka.usersservice.logic.UsersService;
import il.ac.afeka.usersservice.util.exceptions.InvalidInputException;
import il.ac.afeka.usersservice.util.exceptions.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = {"/users"})
public class ReactiveUsersController {
    private final UsersService usersService;

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


    @PutMapping(path = "/{email}/department", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<UserBoundary> linkUserToDepartment(
            @PathVariable("email") String email,
            @RequestBody DepartmentWrapperBoundary department) {

        return this.usersService
                .linkUserToDepartment(email, department)
                .log();
    }

    @DeleteMapping
    public Mono<Void> deleteAll() {
        return this.usersService.deleteAll().log();
    }

    private Flux<UserBoundary> getUsersByCriteria(String criteria, String value) {
        switch (criteria) {
            case "byLastname":
                return this.usersService.getUsersByLastname(value).log();
            case "byMinimumAge":
                try {
                    return this.usersService.getUsersByMinimumAge(Integer.parseInt(value)).log();
                } catch (NumberFormatException e) {
                    return Flux.error(new InvalidInputException("Age must be a number"));
                }
            case "byDomain":
                return this.usersService.getUsersByDomain(value).log();
            case "byDepartmentId":
                return this.usersService.getUserByDepartmentId(value).log();
            default:
                return Flux.error(new NotFoundException("Criteria not found"));
        }
    }
}
