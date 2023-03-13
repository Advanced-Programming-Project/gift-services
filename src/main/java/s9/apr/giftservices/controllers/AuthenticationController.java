package s9.apr.giftservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import s9.apr.giftservices.entities.AuthenticationRequest;
import s9.apr.giftservices.entities.AuthenticationResponse;
import s9.apr.giftservices.entities.Tutor;
import s9.apr.giftservices.entities.UserPrincipal;
import s9.apr.giftservices.security.JWTUtil;
import s9.apr.giftservices.services.TutorService;

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
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.email(),
                        authenticationRequest.password()));
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthenticationResponse(user.getUsername(), token);
    }
    @PostMapping("/register")
    public Tutor saveTutor(@RequestBody Tutor tutor) {
        String encodedPassword = passwordEncoder.encode(tutor.getPassword());
        tutor.setPassword(encodedPassword);
        return tutorService.save(tutor);
    }
    @PostMapping("/logout")
    public String logout() {
        return "successfully logout";
    }
}
