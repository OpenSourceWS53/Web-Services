package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.exceptions;

public class CityFoundException extends RuntimeException{
    public CityFoundException(String name) {
        super("City with name " + name + " already exists");
    }
}
