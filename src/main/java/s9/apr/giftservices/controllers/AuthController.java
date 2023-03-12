package s9.apr.giftservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import s9.apr.giftservices.entities.Tutor;
import s9.apr.giftservices.services.AuthService;
import s9.apr.giftservices.services.TutorService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public String saveTutor(@RequestBody Tutor tutor) {
        return authService.signin(tutor);
    }

}
