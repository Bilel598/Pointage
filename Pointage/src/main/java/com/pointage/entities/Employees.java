package com.pointage.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Employees")
public class Employees implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8567106902501383761L;
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Long id;
	private String date_naissance,nom,email,prenom;
	@OneToOne
	private Carte carte;
	

	public Carte getCarte() {
		return carte;
	}
	public void setCarte(Carte carte) {
		this.carte = carte;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDate_naissance() {
		return date_naissance;
	}
	public void setDate_naissance(String date_naissance) {
		this.date_naissance = date_naissance;
	}
	
	
	public Employees(Long id, String date_naissance, String nom, String email, String prenom, Carte carte) {
		super();
		this.id = id;
		this.date_naissance = date_naissance;
		this.nom = nom;
		this.email = email;
		this.prenom = prenom;
		this.carte = carte;
	}
	@Override
	public String toString() {
		return "Employees [id=" + id + ", date_naissance=" + date_naissance + ", nom=" + nom + ", email=" + email
				+ ", prenom=" + prenom + ", carte=" + carte + "]";
	}
	public Employees() {
		super();
	}
	
	
	
}
