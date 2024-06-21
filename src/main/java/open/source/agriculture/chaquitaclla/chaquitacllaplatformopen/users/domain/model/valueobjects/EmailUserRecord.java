package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record EmailUserRecord(String emailUser) {
    public EmailUserRecord(){
        this(null);
    }
    public EmailUserRecord{
        if (emailUser == null || emailUser.isBlank()) {
            throw new IllegalArgumentException("Email user is required");
        }
    }
}
