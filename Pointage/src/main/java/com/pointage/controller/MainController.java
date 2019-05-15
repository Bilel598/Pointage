package com.pointage.controller;



import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pointage.mqtt.SimpleMqttCallBack;

@Controller
public class MainController {

	//	@Autowired
	//	private PresenceService presenceService;
	//	@Autowired
	//	private EmployeesService employeeService;
	String msg ="test";
	@Autowired
	SimpleMqttCallBack simpleMqttCallBack;
//	String test = simpleMqttCallBack.getHeure();

	public void reception() throws MqttException{

		System.out.println("== START SUBSCRIBER ==");
		MqttClient client=new MqttClient("tcp://192.168.1.52:1883", MqttClient.generateClientId());
		client.setCallback( new SimpleMqttCallBack() );
		client.connect();

		client.subscribe("iot_data");
	}

	@RequestMapping(value="/")
	public String home() throws MqttException {
		reception();
		System.out.println("detect heure "+simpleMqttCallBack.getHeure());
		boolean trigger=false;
//		while(trigger==false) {
//			System.out.println("detect heure "+simpleMqttCallBack.getHeure());
//		}
//		
		while(trigger!=false) {
			String date=simpleMqttCallBack.getDate();
			String heure=simpleMqttCallBack.getHeure();
			String UID=simpleMqttCallBack.getUID();
			System.out.println(UID+" a pointé à "+ heure + " le "+date);	
		}
		return "bienvenue";
	}	
}
