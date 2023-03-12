package s9.apr.giftservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import s9.apr.giftservices.entities.Tutor;

import java.util.Optional;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

    Optional<Tutor> findByEmailAndPassword(String email, String password);
}
