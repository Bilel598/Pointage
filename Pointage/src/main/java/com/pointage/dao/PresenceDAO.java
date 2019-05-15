package com.pointage.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pointage.entities.Presence;

public interface PresenceDAO extends JpaRepository<Presence, Long>{

	Presence findPresenceByDate(String date);

}
