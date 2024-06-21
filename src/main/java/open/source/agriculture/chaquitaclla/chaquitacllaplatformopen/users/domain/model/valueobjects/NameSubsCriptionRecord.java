package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record NameSubsCriptionRecord(String subscriptionName) {

    public NameSubsCriptionRecord(){
        this(null);
    }

    public NameSubsCriptionRecord {
        if (subscriptionName == null || subscriptionName.isBlank()) {
            throw new IllegalArgumentException("Subscription name is required");
        }

    }


}
