package com.pointage.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "Presence")
public class Presence implements Serializable {
	
	private static final long serialVersionUID = -7549178500240814334L;
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Long id;
	private String date;
	private String heure_arrivee;
	private String heure_depart;
	@ManyToOne
	private Employees e;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHeure_arrivee() {
		return heure_arrivee;
	}
	public void setHeure_arrivee(String heure_arrivee) {
		this.heure_arrivee = heure_arrivee;
	}
	public String getHeure_depart() {
		return heure_depart;
	}
	public void setHeure_depart(String heure_depart) {
		this.heure_depart = heure_depart;
	}
	public Employees getE() {
		return e;
	}
	public void setE(Employees e) {
		this.e = e;
	}
	@Override
	public String toString() {
		return "Presence [id=" + id + ", date=" + date + ", heure_arrivee=" + heure_arrivee + ", heure_depart="
				+ heure_depart + ", e=" + e + "]";
	}
	public Presence(Long id, String date, String heure_arrivee, String heure_depart, Employees e) {
		super();
		this.id = id;
		this.date = date;
		this.heure_arrivee = heure_arrivee;
		this.heure_depart = heure_depart;
		this.e = e;
	}
	public Presence() {
		super();
	}
	
	
	
	

}
