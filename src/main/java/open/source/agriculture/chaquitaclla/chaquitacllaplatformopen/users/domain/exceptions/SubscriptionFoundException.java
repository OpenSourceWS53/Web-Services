package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.exceptions;

public class SubscriptionFoundException extends RuntimeException
{
    public SubscriptionFoundException(String name) {
        super("Subscription with name " + name + " already exists");
    }
}
