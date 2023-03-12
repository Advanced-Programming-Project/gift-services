package s9.apr.giftservices.services;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import s9.apr.giftservices.entities.Tutor;

public interface TutorService {
    Tutor save(Tutor tutor);
    Tutor update(long tutorId, Tutor tutor) throws EntityExistsException;

    Tutor findById(long tutorId) throws EntityNotFoundException;

}
