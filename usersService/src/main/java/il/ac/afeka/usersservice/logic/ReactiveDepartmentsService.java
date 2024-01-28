package il.ac.afeka.usersservice.logic;

import il.ac.afeka.usersservice.boundaries.DepartmentBoundary;
import il.ac.afeka.usersservice.util.exceptions.AlreadyExistsException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveDepartmentsService implements DepartmentsService {
    private final ReactiveDepartmentCrud departmentCrud;

    public ReactiveDepartmentsService(ReactiveDepartmentCrud departmentCrud) {
        this.departmentCrud = departmentCrud;
    }

    @Override
    public Mono<DepartmentBoundary> createDepartment(DepartmentBoundary department) {
        return this.departmentCrud.findById(department.getDeptId())
                .flatMap(existingDepartment ->
                        Mono.<DepartmentBoundary>error(new AlreadyExistsException("Department already exists")))
                .switchIfEmpty(this.departmentCrud.save(department.toEntity()).map(DepartmentBoundary::new));
    }

    @Override
    public Flux<DepartmentBoundary> getAll() {
        return this.departmentCrud
                .findAll()
                .map(DepartmentBoundary::new);
    }

    @Override
    public Mono<DepartmentBoundary> getDepartmentById(String id) {
        return this.departmentCrud
                .findById(id)
                .map(DepartmentBoundary::new);
    }

    @Override
    public Mono<Void> deleteAll() {
        return this.departmentCrud.deleteAll();
    }
}
