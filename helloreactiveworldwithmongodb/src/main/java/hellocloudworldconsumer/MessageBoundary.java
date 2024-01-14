package hellocloudworldconsumer;

public class MessageBoundary {
	private String id;
	private String message;

	public MessageBoundary() {
	}

	public MessageBoundary(MessageEntity entity) {
		this.id = entity.getId();
		this.message = entity.getMessage();
	}

	public MessageBoundary(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public MessageEntity toEntity() {
		MessageEntity entity = new MessageEntity();
		
		entity.setId(this.getId());
		entity.setMessage(this.getMessage());
		
		return entity;
	}
	
	@Override
	public String toString() {
		return "MessageBoundary ["
			+ "id = " + id + ", "
			+ "message=" + message + "]";
	}

}
