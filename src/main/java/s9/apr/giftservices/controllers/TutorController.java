package s9.apr.giftservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import s9.apr.giftservices.dtos.StudentDTO;
import s9.apr.giftservices.entities.Student;
import s9.apr.giftservices.entities.Tutor;
import s9.apr.giftservices.mappers.StudentDTOMapper;
import s9.apr.giftservices.services.StudentService;
import s9.apr.giftservices.services.TutorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost", "http://170.64.166.225"})
@RequestMapping("/tutors")
public class TutorController {
    private final TutorService tutorService;
    private final StudentService studentService;

    @Autowired
    public TutorController(TutorService tutorService, StudentService studentService) {
        this.tutorService = tutorService;
        this.studentService = studentService;
    }

    @PostMapping
    public Tutor saveTutor(@RequestBody Tutor tutor) {
        return tutorService.save(tutor);
    }

    @PutMapping("/{tutorId}")
    public Tutor updateTutor(@PathVariable long tutorId, @RequestBody Tutor tutor) {
        return tutorService.update(tutorId, tutor);
    }

    @PostMapping("/students/{tutorId}")
    public StudentDTO saveStudent(@PathVariable long tutorId, @RequestBody StudentDTO studentDTO) {
        Tutor tutor = tutorService.findById(tutorId);
        Student s = studentService.save(StudentDTOMapper.toStudent(studentDTO, tutor));
        return StudentDTOMapper.toDTO(s);
    }

    @GetMapping("/students/{tutorId}")
    public List<StudentDTO> findAllStudents(@PathVariable long tutorId) {
        return studentService.findAllByTutorId(tutorId)
                .stream()
                .map(StudentDTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/students/{tutorId}/{studentId}")
    public StudentDTO updateStudent(@PathVariable long tutorId, @PathVariable long studentId, @RequestBody StudentDTO studentDTO) {
        Tutor tutor = tutorService.findById(tutorId);
        studentDTO.setId(studentId);
        Student s = studentService.udapte(StudentDTOMapper.toStudent(studentDTO, tutor));
        return StudentDTOMapper.toDTO(s);
    }

    @DeleteMapping("/students/{studentId}")
    public boolean deleteStudent(@PathVariable long studentId) {
        return studentService.deleteById(studentId);
    }

}
