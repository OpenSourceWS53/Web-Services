package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record NameSubsCriptionRecord(String subscriptionName) {
    //Constructor
    public NameSubsCriptionRecord(){
        this(null);
    }
    //Validation
    public void validate() {
        if (subscriptionName == null || subscriptionName.isBlank()) {
            throw new IllegalArgumentException("Subscription name is required");
        }

    }
    //Methods
    public String describeName() {
        return subscriptionName ;
    }

}
