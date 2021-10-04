package com.spring.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.dao.StudentRepo;
import com.spring.demo.model.Student;

@RestController
public class StudentController {
	@Autowired
	StudentRepo repo;

	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}

	@PostMapping(path="/student", consumes= {"application/json"})
	public Student addStudent(@RequestBody Student student) {
		repo.save(student);
		return student;
	}
//	@RequestMapping("/getStudent")
//	public ModelAndView getStudent( @RequestParam int sid) {
//		ModelAndView mv=new ModelAndView("showstudent.jsp");
//		Student  student=repo.findById(sid).orElse(new Student());
//		System.out.println(repo.findByMarks(90));
//		System.out.println(repo.findByMarksGreaterThan(80));
//		System.out.println(repo.findByMarksSorted(90));
//	mv.addObject(student);
//		return mv;
//	}
	
	@GetMapping(path="/students")
	public List<Student> getStudents() {
		
		
		return repo.findAll();
	}
	
	@RequestMapping("/student/{sid}")
	public Optional<Student> getStudent(@PathVariable("sid") int sid) {
		
		
		return repo.findById(sid);
	}
	
	@DeleteMapping("/student/{sid}")
	public String deleteStudent(@PathVariable int sid) {
		Student s = repo.getOne(sid);
		repo.delete(s);
		return "Data deleted";
	}
	
	@PutMapping(path="/student", consumes= {"application/json"})
	public Student updateStudent(@RequestBody Student student) {
		repo.save(student);
		return student;
	}
	
}
