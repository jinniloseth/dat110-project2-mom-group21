package no.hvl.dat110.iotsystem;

import no.hvl.dat110.broker.Dispatcher;
import no.hvl.dat110.broker.Storage;
import no.hvl.dat110.client.Client;
import no.hvl.dat110.common.TODO;
import no.hvl.dat110.messages.CreateTopicMsg;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.SubscribeMsg;

public class DisplayDevice {
	
	private static final int COUNT = 10;
		
	public static void main (String[] args) {
		
		System.out.println("Display starting ...");
		
		// TODO - START
				
		// create a client object and use it to
		
		// - connect to the broker - use "display" as the username
		// - create the temperature topic on the broker
		// - subscribe to the topic
		// - receive messages on the topic
		// - unsubscribe from the topic
		// - disconnect from the broker
		
		// TODO - END
		
		Client display = new Client("display", Common.BROKERHOST, Common.BROKERPORT);
		
		display.connect();
		
		display.createTopic("temperature");
		
		display.subscribe("temperature");

		for (int i = 0; i < COUNT; i++) {
			Message msg = display.receive();
		    System.out.println("Received: " + msg.toString());
		}

		display.unsubscribe("temperature");
		
		display.disconnect();
		
		
		System.out.println("Display stopping ... ");
		
	}
}
