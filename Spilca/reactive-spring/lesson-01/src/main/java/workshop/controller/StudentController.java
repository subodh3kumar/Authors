package workshop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import workshop.model.Student;
import workshop.service.StudentService;

@Slf4j
@RestController
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping(value = "/students", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> getStudents() {
        log.info("getStudents() method start");
        return service.getAllStudents();
    }
}
