package com.pointage.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Employees")
public class Employees implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8567106902501383761L;
	private Long id;
	private String nom, prenom;
	@OneToOne
	private Card card;
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
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	@Override
	public String toString() {
		return "Employees [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", card=" + card + "]";
	}
	public Employees(Long id, String nom, String prenom, Card card) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.card = card;
	}
	public Employees() {
		super();
	}
	
}
