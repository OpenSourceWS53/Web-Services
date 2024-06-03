package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//slight cahnge
@SpringBootApplication
@EnableJpaAuditing
public class ChaquitacllaPlatformOpenApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChaquitacllaPlatformOpenApplication.class, args);
	}

}
