package s9.apr.giftservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import s9.apr.giftservices.entities.Tutor;
import s9.apr.giftservices.entities.UserPrincipal;
import s9.apr.giftservices.repositories.TutorRepository;

@Service
public class TutorDetailsServiceImpl implements UserDetailsService {

    private TutorRepository tutorRepository;

    @Autowired
    public TutorDetailsServiceImpl(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Tutor tutor = tutorRepository.findByEmail(email);
        if(tutor == null)
            throw new UsernameNotFoundException("User not found");
        return UserPrincipal.create(tutor);
    }
}
