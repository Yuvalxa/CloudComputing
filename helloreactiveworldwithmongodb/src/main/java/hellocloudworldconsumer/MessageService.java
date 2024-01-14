package hellocloudworldconsumer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MessageService {

	public Mono<MessageBoundary> getMessageById(String id);

	public Mono<MessageBoundary> createMessage(MessageBoundary message);

	public Flux<MessageBoundary> getAll();
	public Flux<MessageBoundary> getByPrefix(String prefix);

	public Mono<Void> deleteAll();


}
