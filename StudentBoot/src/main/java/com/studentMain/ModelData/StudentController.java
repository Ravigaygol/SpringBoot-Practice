package com.studentMain.ModelData;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	@Autowired
	private StudentService stService;

	@GetMapping("/student")
	public List<Student> getAllStudents() {
		return stService.getAllStudent();
	}

	@RequestMapping("/student/{sid}")
	public Student getStudentById(@PathVariable int sid) {
		return stService.getStudentById(sid);
	}

	@RequestMapping("/student/sname/{sname}")
	public Student getStudentByName(@PathVariable("sname") String sname) {
		return stService.getStudentByName(sname);
	}

	@GetMapping("/student/course/{course}")
	public Student getStudentByCourse(@PathVariable("course") String course) {
		return stService.getStudentByCourse(course);
	}

	@PostMapping("/addstudent")
	public Student addStudent(@RequestBody Student st) {
		return this.stService.addStudent(st);
	}

	@PutMapping("/student/{sid}")
	public void updateStudentById(@RequestBody Student sst, @PathVariable int sid) {
		stService.updateStudentById(sst, sid);
	}

	@DeleteMapping("/student/{sid}")
	public void deleteStudentById(@PathVariable int sid) {
		stService.deleteStudentById(sid);
	}

}
