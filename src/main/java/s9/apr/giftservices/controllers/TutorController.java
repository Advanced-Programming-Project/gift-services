package s9.apr.giftservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import s9.apr.giftservices.entities.Tutor;
import s9.apr.giftservices.services.TutorService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tutors")
public class TutorController {
    private final TutorService tutorService;

    @Autowired
    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @PostMapping("/")
    public Tutor saveTutor(@RequestBody Tutor tutor) {
        return tutorService.save(tutor);
    }

}
