package no.hvl.dat110.messages;

public class SubscribeMsg extends Message {

	// message sent from client to subscribe on a topic 

	private String topic;
	
    public SubscribeMsg(String user, String topic) {
    	super(MessageType.SUBSCRIBE, user);
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
		return "SubscribeMsg:{ \n" + "User: " + getUser() + "\n" + "Topic: " + topic + "}";
	}
}
