package no.hvl.dat110.broker;

import java.util.Set;
import java.util.Collection;

import no.hvl.dat110.common.TODO;
import no.hvl.dat110.common.Logger;
import no.hvl.dat110.common.Stopable;
import no.hvl.dat110.messages.*;
import no.hvl.dat110.messagetransport.Connection;

public class Dispatcher extends Stopable {

	private Storage storage;

	public Dispatcher(Storage storage) {
		super("Dispatcher");
		this.storage = storage;

	}

	@Override
	public void doProcess() {

		Collection<ClientSession> clients = storage.getSessions();

		Logger.lg(".");
		for (ClientSession client : clients) {

			Message msg = null;

			if (client.hasData()) {
				msg = client.receive();
			}

			// a message was received
			if (msg != null) {
				dispatch(client, msg);
			}
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void dispatch(ClientSession client, Message msg) {

		MessageType type = msg.getType();

		// invoke the appropriate handler method
		switch (type) {

		case DISCONNECT:
			onDisconnect((DisconnectMsg) msg);
			break;

		case CREATETOPIC:
			onCreateTopic((CreateTopicMsg) msg);
			break;

		case DELETETOPIC:
			onDeleteTopic((DeleteTopicMsg) msg);
			break;

		case SUBSCRIBE:
			onSubscribe((SubscribeMsg) msg);
			break;

		case UNSUBSCRIBE:
			onUnsubscribe((UnsubscribeMsg) msg);
			break;

		case PUBLISH:
			onPublish((PublishMsg) msg);
			break;

		default:
			Logger.log("broker dispatch - unhandled message type");
			break;

		}
	}

	// called from Broker after having established the underlying connection
	public void onConnect(ConnectMsg msg, Connection connection) {

		String user = msg.getUser();

		Logger.log("onConnect:" + msg.toString());

		storage.addClientSession(user, connection);

	}

	// called by dispatch upon receiving a disconnect message
	public void onDisconnect(DisconnectMsg msg) {

		String user = msg.getUser();

		Logger.log("onDisconnect:" + msg.toString());

		storage.removeClientSession(user);

	}

	public void onCreateTopic(CreateTopicMsg msg) {

		Logger.log("onCreateTopic:" + msg.toString());

		// TODO: create the topic in the broker storage
		// the topic is contained in the create topic message
		
		String topic = msg.getTopic();
		
		storage.createTopic(topic);

		Logger.log(topic + " was created");

	}

	public void onDeleteTopic(DeleteTopicMsg msg) {

		Logger.log("onDeleteTopic:" + msg.toString());

		// TODO: delete the topic from the broker storage
		// the topic is contained in the delete topic message
		
		String topic = msg.getTopic();
		storage.deleteTopic(topic);
		
		Logger.log(topic + " was deleted");
		
	}

	public void onSubscribe(SubscribeMsg msg) {

		Logger.log("onSubscribe:" + msg.toString());

		// TODO: subscribe user to the topic
		// user and topic is contained in the subscribe message
		
		
		String topic = msg.getTopic();
		String user = msg.getUser();
		
		storage.addSubscriber(user, topic);
		
		Logger.log("added subscriber with user: " + user + " and topic: " + topic);

	}

	public void onUnsubscribe(UnsubscribeMsg msg) {

		Logger.log("onUnsubscribe:" + msg.toString());

		// TODO: unsubscribe user to the topic
		// user and topic is contained in the unsubscribe message
		
		String topic = msg.getTopic();
		String user = msg.getUser();
		
		storage.removeSubscriber(user, topic);
		
		Logger.log("Removed subscriber with user: " + user + " and topic: " + topic);

	}

	public void onPublish(PublishMsg msg) {

		Logger.log("onPublish:" + msg.toString());

		// TODO: publish the message to clients subscribed to the topic
		// topic and message is contained in the subscribe message
		// messages must be sent using the corresponding client session objects
		
		String topic = msg.getTopic();
		String messageContent = msg.getMessage();
		
		
		Set<String> subscribers = storage.getSubscribers(topic);
		
		if (subscribers == null || subscribers.isEmpty()) {
	        Logger.log("No subscribers for topic: " + topic);
	        return;
	    }
		
		for(String s : subscribers) {
			ClientSession session = storage.getSession(s);
			
			if (session != null) {
	   
				PublishMsg forwardMsg = new PublishMsg(s, topic, messageContent);
	            session.send(forwardMsg);
	        } else {
	            Logger.log("No session found for subscriber: " + s);
	        }
			
		}
	}
}
