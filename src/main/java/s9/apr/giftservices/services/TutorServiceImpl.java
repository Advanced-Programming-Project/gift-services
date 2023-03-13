package s9.apr.giftservices.services;


import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s9.apr.giftservices.entities.Tutor;
import s9.apr.giftservices.repositories.TutorRepository;


@Service
public class TutorServiceImpl implements TutorService {

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
    public Tutor update(Tutor tutor) throws EntityExistsException{
        if(!tutorRepository.existsById(tutor.getId()))
            throw new EntityExistsException("Tutor " + tutor.getId() + " doesn't exist");
        return tutorRepository.save(tutor);
    }

    @Override
    public Tutor findByEmail(String email) throws EntityNotFoundException {
        Tutor t = tutorRepository.findByEmail(email);
        if(t == null)
            throw new EntityNotFoundException("Tutor not found");
        return t;
    }

    @Override
    public Tutor findById(long tutorId) throws EntityNotFoundException {
        return tutorRepository.findById(tutorId)
                .orElseThrow(() -> new EntityNotFoundException("Tutor " + tutorId + " not found"));
    }

}
