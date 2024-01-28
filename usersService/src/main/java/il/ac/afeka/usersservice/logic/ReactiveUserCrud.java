package il.ac.afeka.usersservice.logic;

import il.ac.afeka.usersservice.data.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

public interface ReactiveUserCrud extends ReactiveMongoRepository<UserEntity, String> {

    Flux<UserEntity> findByEmailEndingWith(@Param("domain") String domain);

    Flux<UserEntity> findAllByLastname(@Param("lastname") String lastname);
}
