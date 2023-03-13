package s9.apr.giftservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping("/tutors")
@CrossOrigin
public class TutorController {
    private final TutorService tutorService;
    private final StudentService studentService;

    @Autowired
    public TutorController(TutorService tutorService, StudentService studentService) {
        this.tutorService = tutorService;
        this.studentService = studentService;
    }

    @PutMapping
    public Tutor updateTutor(@RequestBody Tutor tutor) {
        Tutor t = tutorService.findByEmail(tutor.getEmail());
        tutor.setId(t.getId());
        return tutorService.update(tutor);
    }

    @PostMapping("/students")
    public StudentDTO saveStudent(@RequestBody StudentDTO studentDTO) {
        Tutor tutor = tutorService.findByEmail(getAuthenticatedTutorEmail());
        Student s = studentService.save(StudentDTOMapper.toStudent(studentDTO, tutor));
        return StudentDTOMapper.toDTO(s);
    }
    @GetMapping("/students")
    public List<StudentDTO> findAllStudents() {
        Tutor tutor = tutorService.findByEmail(getAuthenticatedTutorEmail());
        return studentService.findAllByTutorId(tutor.getId())
                .stream()
                .map(StudentDTOMapper::toDTO)
                .collect(Collectors.toList());
    }
    @PutMapping("/students/{studentId}")
    public StudentDTO updateStudent(@PathVariable long studentId, @RequestBody StudentDTO studentDTO) {
        Tutor tutor = tutorService.findByEmail(getAuthenticatedTutorEmail());
        studentDTO.setId(studentId);
        Student s = studentService.udapte(StudentDTOMapper.toStudent(studentDTO, tutor));
        return StudentDTOMapper.toDTO(s);
    }
    @DeleteMapping("/students/{studentId}")
    public boolean deleteStudent(@PathVariable long studentId) {
        return studentService.deleteById(studentId);
    }

    private String getAuthenticatedTutorEmail() {
        Tutor tutor = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            tutor = tutorService.findByEmail(email);
        }
        return tutor != null ? tutor.getEmail() : null;
    }
}
