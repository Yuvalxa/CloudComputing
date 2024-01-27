package il.ac.afeka.usersservice.logic;

import il.ac.afeka.usersservice.boundaries.department.DepartmentBoundary;
import il.ac.afeka.usersservice.repositories.ReactiveDepartmentRepository;
import il.ac.afeka.usersservice.util.exceptions.AlreadyExistsException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveDepartmentsService implements DepartmentsService {
    private final ReactiveDepartmentRepository departmentRepository;

    public ReactiveDepartmentsService(ReactiveDepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Mono<DepartmentBoundary> createDepartment(DepartmentBoundary department) {
        return this.departmentRepository.findById(department.getDeptId())
                .flatMap(existingDepartment ->
                        Mono.<DepartmentBoundary>error(new AlreadyExistsException("Department already exists")))
                .switchIfEmpty(this.departmentRepository.save(department.toEntity()).map(DepartmentBoundary::new));
    }

    @Override
    public Flux<DepartmentBoundary> getAll() {
        return this.departmentRepository
                .findAll()
                .map(DepartmentBoundary::new);
    }

    @Override
    public Mono<DepartmentBoundary> getDepartmentById(String id) {
        return this.departmentRepository
                .findById(id)
                .map(DepartmentBoundary::new);
    }

    @Override
    public Mono<Void> deleteAll() {
        return this.departmentRepository.deleteAll();
    }
}
