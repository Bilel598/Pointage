package com.pointage.mqtt;

import java.util.LinkedList;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

@Service
public class SimpleMqttCallBack implements MqttCallback {

	public static LinkedList<String> s = new LinkedList<String>();
	public static boolean detect = false;
	public static String msg;
	

	@Override
	public void connectionLost(Throwable cause) {

		System.out.println("Connection to MQTT broker lost!");
		System.out.println(cause);
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		detect=true;
		msg= new String (message.getPayload());
		s.add (msg);
		if(s.size()==5) 
			s.removeFirst();
		Thread.sleep(500);
		detect=false;
	}
	
	
	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("OK");
	}

}
