package com.jsp.services.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String employeeName;
	@Column(nullable = false,unique = true)
	private String employeeEmail;
	@Column(nullable = false)
	private long employeeAssignedProject;
	@Column(nullable = false)
	private String employeeBaseLocation;
}
