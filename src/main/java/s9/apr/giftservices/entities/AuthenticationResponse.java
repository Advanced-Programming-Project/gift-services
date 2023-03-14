package s9.apr.giftservices.entities;

import java.util.List;

public record AuthenticationResponse (
        String email,
        String token,
        List<Student> studentList
        ) {}
