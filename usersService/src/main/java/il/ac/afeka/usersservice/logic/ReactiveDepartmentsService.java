package il.ac.afeka.usersservice.logic;
import il.ac.afeka.usersservice.boundaries.DepartmentBoundary;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveDepartmentsService implements DepartmentsService {
    private ReactiveDepartmentCrud departmentCrud;

    public ReactiveDepartmentsService(ReactiveDepartmentCrud departmentCrud) {
        this.departmentCrud = departmentCrud;
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
