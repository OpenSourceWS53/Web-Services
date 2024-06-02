package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record NameSubsCriptionRecord(String subscriptionName, Integer subscriptionType) {
    //Constructor
    public NameSubsCriptionRecord(){
        this(null, 1);
    }
    //Validation
    public void validate() {
        if (subscriptionName == null || subscriptionName.isBlank()) {
            throw new IllegalArgumentException("Subscription name is required");
        }
        if (subscriptionType == null || subscriptionType < 1 || subscriptionType > 3) {
            throw new IllegalArgumentException("Subscription type is required");
        }
    }
    //Methods
    public String fullName() {
        return subscriptionName + " " + subscriptionType;
    }

}
