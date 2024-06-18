package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

//value object para el password
@Embeddable
public record Password (String password){

    public Password (){
        this(null);
    }
    public Password {
        if(password == null || password.isEmpty() || password.length() < 8){
            throw new IllegalArgumentException("Invalid password");
        }

    }


}
