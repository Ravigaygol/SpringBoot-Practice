package com.ToDoMain.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ToDoMain.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//   public Map<String, Boolean>deleteByFirstname(User user);
   public User findByFirstname(String firstname);
   public User findByEmail(String email);
   public User findByEmailAndPassword(String email,String password);
}
