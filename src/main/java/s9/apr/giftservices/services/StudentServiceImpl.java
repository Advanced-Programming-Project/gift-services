package s9.apr.giftservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import s9.apr.giftservices.entities.Student;
import org.springframework.stereotype.Service;
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
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    @Override
    public List<Student> saveAll(List<Student> students) {
        return studentRepository.saveAll(students);
    }
}
