package s9.apr.giftservices.services;


import jakarta.persistence.*;
import s9.apr.giftservices.entities.Tutor;

public interface TutorService {
    Tutor save(Tutor tutor);
    Tutor update(Tutor tutor) throws EntityExistsException;
    Tutor findByEmail(String email) throws EntityNotFoundException;
}
