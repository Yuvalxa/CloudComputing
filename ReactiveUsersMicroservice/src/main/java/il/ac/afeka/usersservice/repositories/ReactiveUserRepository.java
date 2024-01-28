package il.ac.afeka.usersservice.repositories;

import il.ac.afeka.usersservice.data.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

public interface ReactiveUserRepository extends ReactiveMongoRepository<UserEntity, String> {

    Flux<UserEntity> findByEmailEndingWith(@Param("domain") String domain);

    Flux<UserEntity> findAllByLastname(@Param("lastname") String lastname);
}
