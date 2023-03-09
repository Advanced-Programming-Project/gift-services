package s9.apr.giftservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import s9.apr.giftservices.entities.Student;
import org.springframework.web.bind.annotation.*;
import s9.apr.giftservices.entities.Tutor;
import s9.apr.giftservices.services.StudentService;
import s9.apr.giftservices.services.TutorService;
import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    //To modify later
    private final TutorService tutorService;

    @Autowired
    public StudentController(StudentService studentService, TutorService tutorService) {
        this.studentService = studentService;
        this.tutorService = tutorService;
    }

    @GetMapping("/generate")
    public List<Student> generateStudents() {
        var students = IntStream
                .rangeClosed(1, 5)
                .mapToObj(i -> Student
                        .builder()
                        .firstname("john " + i)
                        .name("Bobby " + i)
                        .build()).toList();
        Tutor tutor = tutorService.save(new Tutor(1L, "peter", "grec", students)) ;
        students.forEach(s -> s.setTutor(tutor));
        return studentService.saveAll(students);
    }

    @GetMapping("/")
    public List<Student> findAllStudent() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Student findStudentById(@PathVariable long id) {
        return studentService.findById(id);
    }

    @PostMapping("/")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.save(student);
    }
}

