package s9.apr.giftservices.services;

import s9.apr.giftservices.entities.Student;

import java.util.List;

public interface StudentService {
    Student save(Student student);
    List<Student> findAll();
    Student findById(long studentId);

    List<Student> saveAll(List<Student> students);
}
