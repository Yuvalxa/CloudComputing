package il.ac.afeka.usersservice.logic;

import il.ac.afeka.usersservice.data.DepartmentEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ReactiveDepartmentCrud extends ReactiveMongoRepository<DepartmentEntity, String> {
    Mono<Boolean> existsByDepartmentNameIgnoreCase(String departmentName);
}
