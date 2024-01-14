package hellocloudworldconsumer;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = {"/messages"})
public class ReactiveMessageController {
	private MessageService messageService;
	
	public ReactiveMessageController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@GetMapping(
		path = {"/{id}"},
		produces = {MediaType.APPLICATION_JSON_VALUE})
	public Mono<MessageBoundary> sayHello (
			@PathVariable("id") String id) {

		// create service: messageService of Type: MessageService
		// add the service a getMessageById method that return Mono<MessageBoundary)
		// the service will accesses a Reactive Crud
		// get Mono<MessageEntity> and convert the entity to Boundary
		
		// add other messageService method named insertMessage(MessageBoundary):Mono<MessageBoudnary>
		// the service will invoke the Reactive Crud
		// with save operation that stored the entity to the database 
		return this.messageService 
			.getMessageById (id)
			.log();
	}
	
	@PostMapping(
		consumes = {MediaType.APPLICATION_JSON_VALUE},
		produces = {MediaType.APPLICATION_JSON_VALUE})
	public Mono<MessageBoundary> createMessage (
			@RequestBody MessageBoundary message) {
		return this.messageService
			.createMessage(message)
			.log();
	}
	
	@GetMapping(
		produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
	public Flux<MessageBoundary> getAllMessages (){
		return this.messageService
			.getAll()
			.log();
	}
	
	@GetMapping(
		path = "/getByPrefix/{prefix}",
		produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
	public Flux<MessageBoundary> getMessagesBeginningWith (
			@PathVariable("prefix") String prefix){
		return this.messageService
			.getByPrefix(prefix)
			.log();
	}

	@DeleteMapping
	public Mono<Void> deleteAll(){
		return this.messageService
			.deleteAll()
			.log();
	}
}
