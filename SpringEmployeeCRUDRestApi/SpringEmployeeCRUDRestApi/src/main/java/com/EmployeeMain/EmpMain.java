package com.EmployeeMain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmpMain {

	public static void main(String[] args) {
		SpringApplication.run(EmpMain.class, args);
		System.out.println("Employee CRUD Successfully Run...!");
	}

}
