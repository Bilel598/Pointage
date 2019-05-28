package com.pointage.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Carte")
public class Carte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -644915856805661122L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private String uid;
	private boolean isActive;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "Carte [uid=" + uid + ", isActive=" + isActive + "]";
	}
	public Carte(String uid, boolean isActive) {
		super();
		this.uid = uid;
		this.isActive = isActive;
	}
	public Carte() {
		super();
	}

	
}
