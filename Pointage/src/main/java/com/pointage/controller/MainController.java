package com.pointage.controller;


import javax.validation.Valid;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pointage.entities.Employees;
import com.pointage.entities.Presence;
import com.pointage.mqtt.SimpleMqttCallBack;
import com.pointage.services.CarteService;
import com.pointage.services.EmployeesService;
import com.pointage.services.PresenceService;

@Controller
public class MainController {

	@SuppressWarnings("unused")
	private String heure_depart,heurelue,UIDlue;
	private MqttClient client;
	private boolean isSignedIn;

	@Autowired
	private CarteService carteService;
	@Autowired
	private EmployeesService employeesService;
	@Autowired
	private PresenceService presenceService;

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		if(isSignedIn==false) {
			try {
				signIn();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		read();
	}

	public void signIn(){
		System.out.println("Vous êtes dans la méthode signIn.");
		isSignedIn=true;
		try {
			System.out.println("== START SUBSCRIBER ==");
			client=new MqttClient("tcp://192.168.1.53:1883", MqttClient.generateClientId());
			client.setCallback( new SimpleMqttCallBack() );
			client.connect();
			client.subscribe("iot_data");
		}
		catch (Exception e)
		{
			System.out.println("Exception dans la méthode signIn()");	
		}

	}

	public void signOut() {
		isSignedIn=false;
		try {
			System.out.println("== STOP SUBSCRIBER ==");
			client.disconnect();
		}
		catch (Exception e)
		{
			System.out.println("Exception dans la méthode signOut()");	
		}
	}

	public void read() {
		while(this.isSignedIn) {
			try {
				if(SimpleMqttCallBack.detect==true && SimpleMqttCallBack.s.isEmpty()!=true) { 	//Qd message arrive et que la pile n'est pas vide
					// Division of the message
					String[] tab = SimpleMqttCallBack.s.getLast().split("T");
					UIDlue=tab[2];
					heurelue=tab[1];

					//Looking for the UID read in the stored UID
					if(carteService.listCartes().toString().contains(UIDlue)) {

						//If the card hasn't been read yet
						if (carteService.chercherUneCarteParUID(UIDlue).isActive()==false) {
							carteService.setCarteActive(UIDlue,true);
							enregistrer(SimpleMqttCallBack.s.getLast());
						}
						else{
							heure_depart=heurelue;
							enregistrerDepart(heure_depart,UIDlue);
							carteService.setCarteActive(UIDlue,false);

						}
					}
					else System.out.println("Carte inconnue lue! UID: " + UIDlue +"\n");	
				}
				Thread.sleep(500);
			}
			catch(Exception e) {

			}
		}
	}

	private void enregistrerDepart(String h, String uid) {
		System.out.println("En cours de recherce..");
		Presence presence = presenceService.chercherUnePresenceByCarte(uid);
		System.out.println(presence);
		presence.setHeure_depart(h);
		presenceService.save(presence);
	}

	private void enregistrer(String pointage) {
		Presence presence= new Presence();
		String[] tab = pointage.split("T");
		presence.setDate_arrivee(tab[0]);
		presence.setHeure_arrivee(tab[1]);
		presence.setHeure_depart("En Service");
		presence.setCarte(carteService.chercherUneCarteParUID(tab[2]));
		System.out.println(tab[2]);
		presenceService.save(presence);
	}

	@RequestMapping(value="/employees")
	public String listeDesEmployes(Model model) {

		model.addAttribute("listEmployees",employeesService.listEmployees());
		model.addAttribute("listCarte",carteService.listCartes());
		return "employees";
	}
	
	@RequestMapping(value="/")
	public String home(Model model) {
		return "home";
	}
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}

	@GetMapping("/addemployee")
	public String newEmployee(ModelMap model) {
		Employees emp = new Employees();
		model.addAttribute("employee",emp);
		model.addAttribute("listCarte",carteService.listCartes());
		return "addemployee";
	}
	@PostMapping("/saveemployee")
	public String saveEmployee(@Valid Employees employee, BindingResult result, ModelMap model, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			return "addemployee";
		}
		employeesService.save(employee);
		return "redirect:/";
	}

	@GetMapping("/pointage")
	public String pointage(ModelMap model) {
		model.addAttribute("listPresence",presenceService.listePresence());
		model.addAttribute("listEmployee",employeesService.chercherUnEmployee((long) 1));
		model.addAttribute("listCarte",carteService.listCartes());
		return "pointage";
	}

	@GetMapping("/employee/{id}")
	public String employee(@PathVariable long id,ModelMap model) {
		Employees emp= employeesService.chercherUnEmployee(id);
		model.addAttribute("listCarte",carteService.listCartes());
		model.addAttribute("Employee",emp);
		return "employee";
	}

	@GetMapping("/cartes")
	public String listeDesCartes(ModelMap model) {
		model.addAttribute("listCarte",carteService.listCartes());
		return "cartes";
	}
}	

