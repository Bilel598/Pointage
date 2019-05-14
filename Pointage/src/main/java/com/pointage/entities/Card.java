package com.pointage.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "Card")
public class Card implements Serializable {
	
	private static final long serialVersionUID = -7549178500240814334L;
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long uid;
	

}
