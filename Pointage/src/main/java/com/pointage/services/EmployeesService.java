package com.pointage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pointage.dao.EmployeesDAO;
import com.pointage.entities.Employees;

@Service
public class EmployeesService {
	@Autowired
	private EmployeesDAO employeeDAO;
	
	public List<Employees> listEmployees(){
		return employeeDAO.findAll();
	}
	public void save(Employees employee) {
		employeeDAO.save(employee);
	}
	public void delete(Employees e) {
		employeeDAO.delete(e);
	}
	public Employees chercherUnEmployee(Long id) {
		return employeeDAO.findById(id).get();
	}

}
