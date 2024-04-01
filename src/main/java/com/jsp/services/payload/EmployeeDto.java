package com.jsp.services.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
	private long id;
	private String employeeName;
	private String employeeEmail;
	private String employeeBaseLocation;
	private long projectCode;
	private String projectName;
}
