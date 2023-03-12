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
import s9.apr.giftservices.strings.routes.Routes;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TutorController {
    private final TutorService tutorService;
    private final StudentService studentService;

    @Autowired
    public TutorController(TutorService tutorService, StudentService studentService) {
        this.tutorService = tutorService;
        this.studentService = studentService;
    }

    @PutMapping("/tutors")
    public Tutor updateTutor(@RequestBody Tutor tutor) {
        Tutor t = tutorService.findByEmail(tutor.getEmail());
        tutor.setId(t.getId());
        return tutorService.update(tutor);
    }

    @PostMapping(Routes.STUDENT_BASE_URL)
    public StudentDTO saveStudent(@RequestBody StudentDTO studentDTO) {
        Tutor tutor = tutorService.findByEmail(getAuthenticatedTutorEmail());
        Student s = studentService.save(StudentDTOMapper.toStudent(studentDTO, tutor));
        return StudentDTOMapper.toDTO(s);
    }
    @PostMapping(Routes.STUDENT_BASE_URL + "/all")
    public List<StudentDTO> saveAllStudents(@RequestBody List<StudentDTO> studentDTOS) {
        Tutor tutor = tutorService.findByEmail(getAuthenticatedTutorEmail());
        List<Student> students = studentService.saveAll(studentDTOS.stream().map(dto -> StudentDTOMapper.toStudent(dto, tutor))
                        .collect(Collectors.toList()));
        return studentService.saveAll(students).stream()
                .map(StudentDTOMapper::toDTO)
                .collect(Collectors.toList());
    }
    @GetMapping(Routes.STUDENT_BASE_URL)
    public List<StudentDTO> findAllStudents() {
        Tutor tutor = tutorService.findByEmail(getAuthenticatedTutorEmail());
        return studentService.findAllByTutorId(tutor.getId())
                .stream()
                .map(StudentDTOMapper::toDTO)
                .collect(Collectors.toList());
    }
    @PutMapping(Routes.STUDENT_BASE_URL + "/{studentId}")
    public StudentDTO updateStudent(@PathVariable long studentId, @RequestBody StudentDTO studentDTO) {
        Tutor tutor = tutorService.findByEmail(getAuthenticatedTutorEmail());
        studentDTO.setId(studentId);
        Student s = studentService.udapte(StudentDTOMapper.toStudent(studentDTO, tutor));
        return StudentDTOMapper.toDTO(s);
    }
    @DeleteMapping(Routes.STUDENT_BASE_URL + "/{studentId}")
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
