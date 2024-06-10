package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record NameUserRecord(String firstName, String lastName) {
    //Constructor
    public NameUserRecord(){
        this(null, null);
    }
    //Validation
    public void validate() {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name is required");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name is required");
        }
    }
    //Methods
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
