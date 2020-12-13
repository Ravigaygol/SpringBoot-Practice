package com.ToDoMain.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ToDoMain.Model.User;
import com.ToDoMain.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userrepo;
	public User registerUser(User user)
	{
	return userrepo.save(user);
	}
	
	public User fetchUserByEmail(String email)
	{
		return userrepo.findByEmail(email);
	}
	
	public User fetchUserByEmailAndPassword(String email,String password)
	{
		return userrepo.findByEmailAndPassword( email, password);
	}
}
