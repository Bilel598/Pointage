package com.pointage.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private String uid;
	private String nom, prenom;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
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
	@Override
	public String toString() {
		return "Employees [id=" + id + ", uid=" + uid + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
	public Employees(Long id, Long uid, String nom, String prenom) {
		super();
		this.id = id;
		this.uid = uid;
		this.nom = nom;
		this.prenom = prenom;
	}
	public Employees() {
		super();
	}
	
	
	
}
