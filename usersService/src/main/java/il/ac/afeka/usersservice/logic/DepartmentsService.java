package il.ac.afeka.usersservice.logic;

import il.ac.afeka.usersservice.boundaries.DepartmentBoundary;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DepartmentsService {
    public Flux<DepartmentBoundary>  getAll();
    public Mono<DepartmentBoundary> getDepartmentById(String deptId);
    public Mono<Void> deleteAll();

}
