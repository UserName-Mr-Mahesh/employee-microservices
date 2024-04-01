package com.jsp.services.service;

import com.jsp.services.entity.Employee;
import com.jsp.services.payload.EmployeeDto;

public interface EmployeeService {
	public EmployeeDto saveEmployee(Employee employee);
	public EmployeeDto getEmlpoyeeById(long id);
}
