package hellocloudworldconsumer;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping(path = {"/hello/remote"})
public class HelloController {
	private String remoteUrl;
	private RestClient restClient;
	
	@Value("${afeka.remote.base.service.url:http://localhost:8080/hello}")
	public void setRemoteUrl(String remoteUrl) { 
		System.err.println(remoteUrl);
		this.remoteUrl = remoteUrl;
		this.restClient = RestClient.create(this.remoteUrl);
	}
	
	@GetMapping(
		path = {"/{name}"},
		produces = {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> remoteHello (
			@PathVariable("name") String name){
		return this.restClient
			.get()
			.uri("/{name}", name)
			.retrieve()
			.body(Map.class);
	}

	@GetMapping(
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public Map<String, Object> remoteHello (){
		return this.restClient
			.get()
			.retrieve()
			.body(Map.class);
	}
}
