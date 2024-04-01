package com.jsp.services.serviceImpl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jsp.services.entity.Employee;
import com.jsp.services.payload.Project;
import com.jsp.services.feignclient.ProjectFeign;
import com.jsp.services.payload.EmployeeDto;
import com.jsp.services.repository.EmployeeRepository;
import com.jsp.services.service.EmployeeService;

import feign.Response;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ProjectFeign projectFeign;
	@Override
	public EmployeeDto saveEmployee(Employee employee) {
		Employee saveEmployee=employeeRepository.save(employee);
		Response response=projectFeign.getProjectByCode(saveEmployee.getEmployeeAssignedProject());
//		String body=response.body().toString();
//		System.out.println(body);
//		Gson g=new Gson();
//		Project project=g.fromJson(body, Project.class);
		String responseBody;
		try {
			responseBody = new String(response.body().asInputStream().readAllBytes());
			ObjectMapper objectMapper = new ObjectMapper();
	        Project project = objectMapper.readValue(responseBody, Project.class);		
			EmployeeDto employeeDto=new EmployeeDto();
			employeeDto.setId(saveEmployee.getId());
			employeeDto.setEmployeeName(saveEmployee.getEmployeeName());
			employeeDto.setEmployeeEmail(saveEmployee.getEmployeeEmail());
			employeeDto.setEmployeeBaseLocation(saveEmployee.getEmployeeBaseLocation());
			employeeDto.setProjectCode(project.getProjectCode());
			employeeDto.setProjectName(project.getProjectName());
			return employeeDto;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	@Override
	public EmployeeDto getEmlpoyeeById(long id) {
		Employee foundEmployee = employeeRepository.findById(id).get();
		Response response=projectFeign.getProjectByCode(foundEmployee.getEmployeeAssignedProject());
//		String body=response.body().toString();
//		Gson g=new Gson();
//		Project project=g.fromJson(body, Project.class);
		String responseBody;
		try {
			responseBody = new String(response.body().asInputStream().readAllBytes());
			ObjectMapper objectMapper = new ObjectMapper();
	        Project project = objectMapper.readValue(responseBody, Project.class);
			EmployeeDto employeeDto=new EmployeeDto();
			employeeDto.setId(foundEmployee.getId());
			employeeDto.setEmployeeName(foundEmployee.getEmployeeName());
			employeeDto.setEmployeeEmail(foundEmployee.getEmployeeEmail());
			employeeDto.setEmployeeBaseLocation(foundEmployee.getEmployeeBaseLocation());
			employeeDto.setProjectCode(project.getProjectCode());
			employeeDto.setProjectName(project.getProjectName());
			return employeeDto;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
