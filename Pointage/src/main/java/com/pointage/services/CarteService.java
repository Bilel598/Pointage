package com.pointage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointage.dao.CarteDAO;
import com.pointage.entities.Carte;

@Service
public class CarteService {

	@Autowired
	private CarteDAO cartedao;
	
	public List<Carte> listCartes(){
		return cartedao.findAll();
	}
	public Carte chercherUneCarteParUID(String uid) {
		return cartedao.findOneByUid(uid);
	}
	public void setCarteActive(String uid, boolean t) {
		Carte carte = chercherUneCarteParUID(uid);
		carte.setActive(t);
		cartedao.save(carte);
	}
	public void save(Carte c) {
		cartedao.save(c);
	}
}
