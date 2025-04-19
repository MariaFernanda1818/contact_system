package co.uni.contact.management.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemApplication.class, args);

		// Método extra: Generar un password encriptado
		//generarPasswordEncriptado("Mf1010119827@");
	}

	/*private static void generarPasswordEncriptado(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(password);
		System.out.println("Password encriptado: " + encodedPassword);
	}*/
}
