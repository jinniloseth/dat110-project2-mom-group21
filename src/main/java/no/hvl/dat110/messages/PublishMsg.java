package no.hvl.dat110.messages;

import no.hvl.dat110.common.TODO;

public class PublishMsg extends Message {

	// message sent from client to create publish a message on a topic

	private String topic;

	private String message;

	public PublishMsg(String user, String topic, String message) {
		super(MessageType.PUBLISH, user);
		this.topic = topic;
		this.message = message;
	}

	// TODO:
	// Implement object variables - a topic and a message is required

	// Complete the constructor, get/set-methods, and toString method
	// as described in the project text

	public String getMessage() {
		return message;
	}

	public void setMessage(String m) {
		message = m;
	}

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
		return "PublishMsg:{ \n" + "User: " + getUser() + "\n" + "Topic: " + topic + "\n" + "Message :" + message + "}";
	}

}
