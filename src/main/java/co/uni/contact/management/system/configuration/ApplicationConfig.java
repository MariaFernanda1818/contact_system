package co.uni.contact.management.system.configuration;

import co.uni.contact.management.system.dto.ContactDTO;
import co.uni.contact.management.system.dto.ContactSecurityDTO;
import co.uni.contact.management.system.entity.ContactEntity;
import co.uni.contact.management.system.repository.ContactRepository;
import co.uni.contact.management.system.utils.mappers.ContactMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static co.uni.contact.management.system.utils.constants.Constantes.ERROR_USUARIO_NO_ENCONTRADO;
import static co.uni.contact.management.system.utils.constants.Constantes.ORIGEN_DESARROLLO;

/**
 * Configuración principal para la aplicación.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsContactService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = "userDetailsContactService")
    public UserDetailsService userDetailsContactService() {
        return username -> {
            ContactEntity contact = contactRepository.findByEmailAddress(username)
                    .orElseThrow(() -> new UsernameNotFoundException(ERROR_USUARIO_NO_ENCONTRADO + username));
            ContactDTO contactDTO = contactMapper.entityToDto(contact);

            ContactSecurityDTO contactSecurityDTO = new ContactSecurityDTO(contactDTO);
            contactSecurityDTO.setPassword(contact.getHashPassword());

            return contactSecurityDTO;
        };
    }

    @Bean
    public WebMvcConfigurer corsConfigurerDev() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}
