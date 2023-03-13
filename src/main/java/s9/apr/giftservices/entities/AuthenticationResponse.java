package s9.apr.giftservices.entities;

public record AuthenticationResponse (
        String email,
        String token
        ) {}
