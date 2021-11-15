package com.subhas.school.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		if (studentOptional.isPresent()) {
			throw new IllegalStateException("Student with this Email is already present");
		}
		studentRepository.save(student);
	}

	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		if (!exists) {
			throw new IllegalStateException(String.format("Student with id %d does not exists", studentId));
		}
		studentRepository.deleteById(studentId);
	}

	@Transactional
	public void updateStudent(Long studentId, Optional<String> nameOptional, Optional<String> emailOptional) {
		Optional<Student> studentOptional = studentRepository.findById(studentId);
		if (studentOptional.isEmpty()) {
			throw new IllegalStateException(String.format("Student with id %d does not exists", studentId));
		}
		Student student = studentOptional.get();
		if (nameOptional.isPresent()) {
			updateName(student, nameOptional.get());
		}
		if (emailOptional.isPresent()) {
			updateEmail(student, emailOptional.get());
		}
	}

	private void updateName(Student student, String name) {
		if (!name.isEmpty() && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
	}
	
	private void updateEmail(Student student, String email) {
		if (!email.isEmpty() && !Objects.equals(student.getEmail(), email)) {
			Optional<Student> emailStudent = studentRepository.findStudentByEmail(email);
			if (emailStudent.isEmpty()) {
				student.setEmail(email);
			}
		}
	}
}
