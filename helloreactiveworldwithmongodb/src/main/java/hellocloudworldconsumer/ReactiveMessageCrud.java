package hellocloudworldconsumer;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;

import reactor.core.publisher.Flux;

public interface ReactiveMessageCrud extends 
	ReactiveMongoRepository<MessageEntity, String>{
	
	public Flux<MessageEntity> findAllByMessageLike (
			@Param("pattern") String pattern);
}
