package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;

public class TemperatureDevice {

    private static final int COUNT = 10;

    public static void main(String[] args) {

        // Simulated temperature sensor
        TemperatureSensor sensor = new TemperatureSensor();

        // Create a client object
        Client tempClient = new Client("sensor", Common.BROKERHOST, Common.BROKERPORT);

        // Connect to the broker
        tempClient.connect();

        // Publish temperature readings
        for (int i = 0; i < COUNT; i++) {
            int temperature = sensor.read();
            tempClient.publish("temperature", String.valueOf(temperature));

            // Pause between readings
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                System.err.println("Interrupted: " + e.getMessage());
            }
        }

        // Disconnect from the broker
        tempClient.disconnect();

        System.out.println("Temperature device stopping ... ");
    }
}
