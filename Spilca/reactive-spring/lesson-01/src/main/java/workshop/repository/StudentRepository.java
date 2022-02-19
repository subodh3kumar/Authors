package workshop.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import workshop.model.Student;

public interface StudentRepository extends ReactiveCrudRepository<Student, Integer> {

}
