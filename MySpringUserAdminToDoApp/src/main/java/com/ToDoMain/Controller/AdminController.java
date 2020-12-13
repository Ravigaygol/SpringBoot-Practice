package com.ToDoMain.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ToDoMain.Model.Admin;
import com.ToDoMain.Repository.AdminRepository;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AdminController {

	@Autowired
	private AdminRepository adminrepo;
	
	@PostMapping("/admin")
	public Admin createAdmin(@RequestBody Admin admin) {
		return adminrepo.save(admin);
	}

}
