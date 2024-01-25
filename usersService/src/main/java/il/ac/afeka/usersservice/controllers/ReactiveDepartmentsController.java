package il.ac.afeka.usersservice.controllers;

import il.ac.afeka.usersservice.boundaries.DepartmentBoundary;
import il.ac.afeka.usersservice.logic.DepartmentsService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = {"/departments"})
public class ReactiveDepartmentsController {
    private DepartmentsService departmentsService;

    public ReactiveDepartmentsController(DepartmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }

    // Triggered when no parameters are provided
    @GetMapping(produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public Flux<DepartmentBoundary> getAllDepartments() {
        return this.departmentsService.getAll().log();
    }

    @GetMapping(path = {"/{id}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<DepartmentBoundary> getDepartmentById (
            @PathVariable("id") String id) {
      return this.departmentsService.getDepartmentById(id).log();
    }

    @DeleteMapping
    public Mono<Void> deleteAll() {
        return this.departmentsService.deleteAll().log();
    }
}

