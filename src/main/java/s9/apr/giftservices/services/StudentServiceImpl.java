package s9.apr.giftservices.services;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import s9.apr.giftservices.entities.Student;
import org.springframework.stereotype.Service;
import s9.apr.giftservices.entities.Tutor;
import s9.apr.giftservices.repositories.StudentRepository;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> findAllByTutorId(long tutorId) {
        return studentRepository.findAllByTutorId(tutorId);
    }

    @Override
    public Student findById(long studentId) throws EntityNotFoundException{
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("No student found for " + studentId));
    }
    @Override

    public Student udapte(Student student) throws EntityExistsException {
        if(!studentRepository.existsById(student.getId()))
            throw new EntityExistsException("Student " + student.getId() + " doesn't exist");
        return studentRepository.save(student);
    }
    @Override
    public boolean deleteById(long studentId) {
        if(!studentRepository.existsById(studentId))
            return true;
        studentRepository.deleteById(studentId);
        return !studentRepository.existsById(studentId);
    }

    @Override
    public List<Student> saveAll(List<Student> students) {
        return studentRepository.saveAll(students);
    }
}
