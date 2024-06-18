package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.exceptions;

public class CountryFoundException extends RuntimeException{
    public CountryFoundException(String name) {
        super("Country with name " + name + " already exists");
    }
}
