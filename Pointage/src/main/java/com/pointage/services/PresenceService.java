package com.pointage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointage.dao.PresenceDAO;
import com.pointage.entities.Presence;

@Service
public class PresenceService {
	@Autowired
	private PresenceDAO presenceDAO;

	public List<Presence> listePresence(){
		return presenceDAO.findAll();
	}
	

	public Presence chercherUnePresence(Long id) {
		return presenceDAO.findById(id).get();		
	}
	public Presence chercherUnePresenceByCarte(String uid) {
		return presenceDAO.presenceByUidOnline(uid);
	}
	public Presence chercherUnePresenceByDate(String date) {
		return presenceDAO.findPresenceByDate(date);
	}
	
	public void save(Presence presence) {
		presenceDAO.save(presence);
	}
	public Presence chercherDerniere() {
		return presenceDAO.findFirstByOrderByIdDesc();
	}
}
