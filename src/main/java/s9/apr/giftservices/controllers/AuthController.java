package s9.apr.giftservices.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import s9.apr.giftservices.entities.Tutor;
import s9.apr.giftservices.services.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    private final AuthService authService;
    @Resource(name = "authenticationManager")
    private AuthenticationManager authManager;


    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Tutor> saveTutor(@RequestBody Tutor tutor) {
        try {
            authService.login(tutor);

            //UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(tutor.getEmail(), tutor.getPassword());
            //Authentication auth = authManager.authenticate(authReq);
            //SecurityContext sc = SecurityContextHolder.getContext();
            //sc.setAuthentication(auth);

            return ResponseEntity.status(200).body(authService.login(tutor));
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }

    }

}
