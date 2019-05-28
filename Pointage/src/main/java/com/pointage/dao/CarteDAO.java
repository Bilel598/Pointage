package com.pointage.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pointage.entities.Carte;

public interface CarteDAO extends JpaRepository<Carte, String> {

	Carte findOneByUid(String uid);

}
