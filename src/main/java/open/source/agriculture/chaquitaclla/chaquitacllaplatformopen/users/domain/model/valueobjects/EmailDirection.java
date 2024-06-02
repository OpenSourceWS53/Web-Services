package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;

@Embeddable
public record EmailDirection(String email) {
    //Constructor
    public EmailDirection(){
        this(null);
    }
    //Validation con @ y un alongitud mayor a 5
    public void validate() {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (!email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
            throw new IllegalArgumentException("Email is invalid");
        }
    }
    //Methods
    @Email
    public String email() {

        return email;
    }
}
