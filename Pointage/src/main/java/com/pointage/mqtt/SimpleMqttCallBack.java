package com.pointage.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;
@Service
public class SimpleMqttCallBack implements MqttCallback {
	String date,heure,UID,msg;
	String[] tab;
	boolean detect = false;
	
	

	

	public boolean isDetect() {
		return detect;
	}

	public void setDetect(boolean detect) {
		this.detect = detect;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHeure() {
		return this.heure;
	}

	public void setHeure(String heure) {
		this.heure = heure;
	}

	public String getUID() {
		return UID;
	}

	public void setUID(String uID) {
		UID = uID;
	}

	public String[] getTab() {
		return tab;
	}

	public void setTab(String[] tab) {
		this.tab = tab;
	}

	@Override
	public void connectionLost(Throwable cause) {

		System.out.println("Connection to MQTT broker lost!");
		System.out.println(cause);
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		setDetect(true);
		
		String msg = new String(message.getPayload());
		String[] tab = msg.split("T");
		String date=tab[0];
		setDate(date);
//		String heure=tab[1];
		setHeure("tes");
//		setHeure(heure);
		String UID=tab[2];
		setUID(UID);
//		setDetect(false);
//		System.out.println(getHeure());
	}
	
	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("OK");
	}

}
