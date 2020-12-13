package com.ToDoMain.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ToDoMain.Exception.ResourceNotFoundException;
import com.ToDoMain.Model.User;
import com.ToDoMain.Repository.UserRepository;
import com.ToDoMain.Service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

	@Autowired 
	private UserService uservice;
	/*
	@Autowired
	private UserRepository userrepo;
//--------------------------------------- Without Using User-Service Class--------------------------------------	 

	@GetMapping("/user")
	public List<User> getAllUsers() {
		return userrepo.findAll();
	}

	@GetMapping("/user/{userid}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "userid") int userid)
			throws ResourceNotFoundException {
		User user = userrepo.findById(userid)
				.orElseThrow(() -> new ResourceNotFoundException("User is not found for this id :" + userid));
		return ResponseEntity.ok().body(user);
	}

	@GetMapping("/user1/{firstname}")
	public ResponseEntity<User> getUserByFirstname(@PathVariable(value = "firstname") String firstname) {
		User user = userrepo.findByFirstname(firstname);
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
		return userrepo.save(user);
	}

	@PutMapping("/user/{userid}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "userid") int userid, @RequestBody User userDetails)
			throws ResourceNotFoundException {
		User user = userrepo.findById(userid)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userid));
		user.setFirstname(userDetails.getFirstname());
		user.setLastname(userDetails.getLastname());
		user.setEmail(userDetails.getEmail());
		user.setGender(userDetails.getGender());
		user.setDateOfBirth(userDetails.getDateOfBirth());
		user.setPassword(userDetails.getPassword());
		user.setConfirmPassword(userDetails.getConfirmPassword());
		final User updatedUser = userrepo.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/user/{userid}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "userid") int userid)
			throws ResourceNotFoundException {
		User user = userrepo.findById(userid)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userid));

		userrepo.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("User deleted", Boolean.TRUE);
		return response;
	}

*/	  /*
	  @DeleteMapping("/user2/{firstname}") public Map<String, Boolean>
	  deleteByFname(@PathVariable(value = "firstname") String firstname) { User
	  user=userrepo.findByFirstname(firstname); userrepo.deleteByFirstname(user);
	  Map<String, Boolean>res=new HashMap<>(); res.put("User Deleted",
	   Boolean.TRUE); return res; }*/
//---------------------------------------Using User-Service Class--------------------------------------	 

	@GetMapping("/registeruser")
	public User registerUser(@RequestBody User user) throws Exception
	{
		String tempEmail=user.getEmail();
		if(tempEmail!=null && !"".equals(tempEmail))
		{
			User userobj=uservice.fetchUserByEmail(tempEmail);
			if(userobj!=null) {
				throw new Exception("User with "+tempEmail+" is already exist");
			}
		}
		User userobj=null;
		userobj=uservice.registerUser(user);
		return userobj;
	}
	
	@PostMapping("/loginuser")
	public User loginUser(@RequestBody User user) throws Exception
	{
		String tempEmail=user.getEmail();
		String tempPassword=user.getPassword();
		User userobj=null;
		if(tempEmail!=null && tempPassword!=null)
		{
			userobj=uservice.fetchUserByEmailAndPassword(tempEmail,tempPassword);
		}
		if(userobj ==null)
		{
			throw new Exception("User does not exist.");
		}
		return userobj;
	}
}
