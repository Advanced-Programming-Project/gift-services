package s9.apr.giftservices.services;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s9.apr.giftservices.entities.Tutor;
import s9.apr.giftservices.repositories.TutorRepository;

@Service
public class TutorServiceImpl implements TutorService{

    private final TutorRepository tutorRepository;

    @Autowired
    public TutorServiceImpl(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    @Override
    public Tutor save(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    @Override
    public Tutor update(long tutorId, Tutor tutor) throws EntityExistsException{
        if(!tutorRepository.existsById(tutorId))
            throw new EntityExistsException("Tutor " + tutorId + " doesn't exist");
        return tutorRepository.save(tutor);
    }

    @Override
    public Tutor findById(long tutorId) throws EntityNotFoundException {
        return tutorRepository.findById(tutorId)
                .orElseThrow(() -> new EntityNotFoundException("Tutor " + tutorId + " not found"));
    }
}
