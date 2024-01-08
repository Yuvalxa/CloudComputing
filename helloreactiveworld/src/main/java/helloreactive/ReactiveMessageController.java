package helloreactive;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = {"/hello"})
public class ReactiveMessageController {
	
	@GetMapping(
		path = {"/{name}"},
		produces = {MediaType.APPLICATION_JSON_VALUE})
	public Mono<MessageBoundary> sayHello (
			@PathVariable("name") String name) {
		return Mono.just(new MessageBoundary("Hello " + name))
			.log();
	}
	
	@GetMapping(
		produces = {MediaType.APPLICATION_JSON_VALUE})
	public Mono<MessageBoundary> sayHello () {
		return this.sayHello("World");
	}
	
	@GetMapping(
		path = {"/many/{count}"},
		produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
	public Flux<MessageBoundary> getMessages (
		@PathVariable("count") int count){
		if (count <= 0) {
			count = 1;
		}else if (count > 10) {
			count = 10;
		}
		return Flux.range(1, count)
			.map(i->new MessageBoundary("Message #" + i))
			.log();
	}
}
