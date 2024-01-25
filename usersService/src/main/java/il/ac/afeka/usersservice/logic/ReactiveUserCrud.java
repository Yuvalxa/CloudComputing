package il.ac.afeka.usersservice.logic;

import il.ac.afeka.usersservice.data.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveUserCrud extends ReactiveMongoRepository<UserEntity, String> {

	Flux<UserEntity> findByEmailEndingWith( @Param("domain") String domain );

	Flux<UserEntity> findAllByLastname( @Param("lastname") String lastname );

	Mono<UserEntity> findByEmailAndPassword( @Param("email") String email, @Param("password") String password );

	Mono<UserEntity> findByEmail( @Param("email") String email);
}
