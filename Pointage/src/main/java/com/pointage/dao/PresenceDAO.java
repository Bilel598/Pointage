package com.pointage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pointage.entities.Carte;
import com.pointage.entities.Presence;

public interface PresenceDAO extends JpaRepository<Presence, Long>{

	Presence findPresenceByDate(String date);

	Presence findFirstByOrderByIdDesc();

	Presence findByCarte(Carte c);
	
	@Query("select p from Presence p where p.carte.uid like :uid and heure_depart like 'En Service'")
	public Presence presenceByUidOnline(@Param("uid")String uid);
	
}
