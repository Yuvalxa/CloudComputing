package hellocloudworldconsumer;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveMessageService implements MessageService {
	private ReactiveMessageCrud messageCrud;
	
	public ReactiveMessageService(ReactiveMessageCrud messageCrud) {
		this.messageCrud = messageCrud;
	}
	
	@Override
	public Mono<MessageBoundary> getMessageById(String id) {
		return this.messageCrud
			.findById(id)
			.map(MessageBoundary::new);
	}

	@Override
	public Mono<MessageBoundary> createMessage(MessageBoundary message) {
		// TODO reset id of message
		return Mono.just(message) // beginning reactively
			.map(MessageBoundary::toEntity)
			.flatMap(this.messageCrud::save)// make sure the returned Mono integrates to the reactive flow
			.map(MessageBoundary::new);
	}

	@Override
	public Flux<MessageBoundary> getAll() {
		return this.messageCrud
			.findAll()
			.map(MessageBoundary::new);
	}

	@Override
	public Flux<MessageBoundary> getByPrefix(String prefix) {
		return this.messageCrud
			.findAllByMessageLike(prefix + "*")
			.map(MessageBoundary::new);
	}

	@Override
	public Mono<Void> deleteAll() {
		return this.messageCrud
			.deleteAll();
	}

}
