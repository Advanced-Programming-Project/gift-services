package s9.apr.giftservices.services;


import jakarta.persistence.*;
import s9.apr.giftservices.entities.Student;
import java.util.List;

public interface StudentService {
    Student save(Student student);
    List<Student> findAllByTutorId(long tutorId);
    Student udapte(Student student) throws EntityExistsException;
    Student findById(long studentId) throws EntityNotFoundException;
    boolean deleteById(long studentId);
    List<Student> saveAll(List<Student> students);
}
