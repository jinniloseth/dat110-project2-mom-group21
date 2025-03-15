package no.hvl.dat110.messages;

public class UnsubscribeMsg extends Message {

	// message sent from client to unsubscribe on a topic 

	private String topic;
	
    public UnsubscribeMsg(String user, String topic) {
    	super(MessageType.UNSUBSCRIBE, user);
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
		return "UnsubscribeMsg:{ \n" + "User: " + getUser() + "\n" + "Topic: " + topic +  "}";
	}
    
}
