package no.hvl.dat110.messages;

public class CreateTopicMsg extends Message {

	// message sent from client to create topic on the broker

	private String topic;

	public CreateTopicMsg(String user, String topic) {
	    super(MessageType.CREATETOPIC, user);
	    this.topic = topic;
	}

	// TODO:
	// Implement object variables - a topic is required

	// Complete the constructor, get/set-methods, and toString method
	// as described in the project text

	public String getTopic() {
		return topic;
	}

	public void setTopic(String t) {
		if (t == null) {
	        throw new IllegalArgumentException("Topic cannot be null");
	    }
	    topic = t;
	}
	
	
	public String toString() {
		return "CreateTopicMsg:{ \n" + "User: " + getUser() + "\n" + "Topic: " + topic +  "}";
	}

}
