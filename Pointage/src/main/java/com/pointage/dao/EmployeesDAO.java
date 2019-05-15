package com.pointage.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pointage.entities.Employees;

public interface EmployeesDAO extends JpaRepository<Employees, Long> {

}
