package com.subhas.school.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		//@formatter:off
		return args -> {
			Student alex = new Student(
					"Alex", 
					LocalDate.of(1987, 1, 1), 
					"alex@example.com" 
			);
			Student bob = new Student(
					"Bob", 
					LocalDate.of(1992, 12, 31), 
					"bob@example.com"
			);
			studentRepository.saveAll(
						List.of(alex, bob)
					);

		};
		//@formatter:on
	}
}
