package com.subhas.school.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping
	public ResponseEntity<List<Student>> getStudents() {
		return new ResponseEntity<>(studentService.getStudents(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Student> registerNewStudent(@RequestBody Student student) {
		return new ResponseEntity<>(studentService.addNewStudent(student), HttpStatus.CREATED);
	}

	@DeleteMapping(path = "{studentId}")
	public ResponseEntity<DeleteMessage> deleteStudent(@PathVariable("studentId") Long studentId) {
		studentService.deleteStudent(studentId);
		DeleteMessage deleteMessage = DeleteMessage.createDeleteMessage(String.format("Student with id %d deleted", studentId));
		return new ResponseEntity<>(deleteMessage, HttpStatus.OK);
	}

	// @formatter:off
	@PutMapping(path = "{studentId}")
	public ResponseEntity<Student> updateStudent(@PathVariable("studentId") Long studentId, 
							  @RequestParam(required = false) String name,
							  @RequestParam(required = false) String email) {
		Student student = studentService.updateStudent(studentId, 
									 Optional.ofNullable(name), 
									 Optional.ofNullable(email));
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	// @formatter:on

}
