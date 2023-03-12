package s9.apr.giftservices.repositories;

import s9.apr.giftservices.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s9.apr.giftservices.entities.Tutor;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByTutorId(long tutorId);
}
