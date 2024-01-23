package il.ac.afeka.usersservice.logic;

import il.ac.afeka.usersservice.data.DepartmentEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Mono;

public interface ReactiveDepartmentCrud extends ReactiveMongoRepository<DepartmentEntity, String>{

    Mono<DepartmentEntity> findById(@Param("id") String  id);

    }
