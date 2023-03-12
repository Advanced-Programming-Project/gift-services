package s9.apr.giftservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s9.apr.giftservices.entities.Tutor;
import s9.apr.giftservices.repositories.TutorRepository;

@Service
public class AuthServiceImpl implements AuthService {

    private final TutorRepository tutorRepository;

    @Autowired
    public AuthServiceImpl(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    @Override
    public String signin(Tutor tutor) {
        return "yes";

    }
}
