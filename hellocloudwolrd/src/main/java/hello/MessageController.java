package hello;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/hello"})
public class MessageController {
	
	@GetMapping(
		path = {"/{name}"},
		produces = {MediaType.APPLICATION_JSON_VALUE})
	public MessageBoundary sayHello (
			@PathVariable("name") String name) {
		return new MessageBoundary("Hello " + name);
	}
	
	@GetMapping(
		produces = {MediaType.APPLICATION_JSON_VALUE})
	public MessageBoundary sayHello () {
		return this.sayHello("World");
	}
}
