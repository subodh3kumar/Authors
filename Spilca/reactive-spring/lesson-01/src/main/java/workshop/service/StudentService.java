package workshop.service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import workshop.model.Student;
import workshop.repository.StudentRepository;

@Slf4j
@Service
public class StudentService {

	private final StudentRepository repository;

	public StudentService(StudentRepository repository) {
		this.repository = repository;
	}

	public Flux<Student> getAllStudents() {
		log.info("getAllStudents() method start");
		return repository.findAll().delayElements(Duration.ofSeconds(5));
	}
}
