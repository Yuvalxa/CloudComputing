package il.ac.afeka.usersservice.repositories;

import il.ac.afeka.usersservice.data.DepartmentEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ReactiveDepartmentRepository extends ReactiveMongoRepository<DepartmentEntity, String> {
    Mono<Boolean> existsByDepartmentNameIgnoreCase(String departmentName);
}
