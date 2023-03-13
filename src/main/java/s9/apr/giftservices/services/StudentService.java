package s9.apr.giftservices.services;


import jakarta.persistence.*;
import s9.apr.giftservices.entities.Student;
import s9.apr.giftservices.entities.Tutor;

import java.util.List;

public interface StudentService {
    Student save(Student student);
    List<Student> findAllByTutorId(long tutorId);
    Student findById(long studentId) throws EntityNotFoundException;
    Student udapte(Student student) throws EntityExistsException;
    boolean deleteById(long studentId);
    List<Student> saveAll(List<Student> students);
}
