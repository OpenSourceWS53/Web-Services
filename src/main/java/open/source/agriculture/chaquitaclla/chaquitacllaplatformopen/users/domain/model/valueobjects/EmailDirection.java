package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record EmailDirection(
        @Email
        @NotBlank
        String email) {

    public EmailDirection {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
    }
}