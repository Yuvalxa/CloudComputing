package il.ac.afeka.usersservice.logic;
import il.ac.afeka.usersservice.boundaries.DepartmentBoundary;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveDepartmentsService implements DepartmentsService {
        @Override
        public Flux<DepartmentBoundary> getAll() {
            return null;
        }
        @Override
        public Flux<DepartmentBoundary> getDepartmentById(String id) {
            return null;
        }
        @Override
        public Mono<Void> deleteAll() {
            return null;
        }



}
