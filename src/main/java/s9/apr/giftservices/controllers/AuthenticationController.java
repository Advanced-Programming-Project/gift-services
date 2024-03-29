package s9.apr.giftservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import s9.apr.giftservices.entities.*;
import s9.apr.giftservices.security.JWTUtil;
import s9.apr.giftservices.services.TutorService;
import s9.apr.giftservices.strings.routes.Routes;
@RestController
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final TutorService tutorService;

    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JWTUtil jwtUtil,
                                    TutorService tutorService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.tutorService = tutorService;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping(Routes.LOGIN)
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.email(),
                        authenticationRequest.password()));
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthenticationResponse(user.getUsername(), token, user.getTutor().getStudents());
    }
    @PostMapping(Routes.REGISTER)
    public Tutor saveTutor(@RequestBody Tutor tutor) {
        Tutor dbTutor = tutorService.findByEmail(tutor.getEmail());
        if(dbTutor != null) {
            return null;
        }
        String encodedPassword = passwordEncoder.encode(tutor.getPassword());
        tutor.setPassword(encodedPassword);
        return tutorService.save(tutor);
    }
}
