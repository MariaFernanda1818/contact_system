package co.uni.contact.management.system.service.Impl;

import co.uni.contact.management.system.dto.AuthResponseDTO;
import co.uni.contact.management.system.dto.LoginContactDTO;
import co.uni.contact.management.system.dto.RespuestaGeneralDTO;
import co.uni.contact.management.system.service.ILoginContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class LoginContactService implements ILoginContactService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private static final String INICIO_EXITOSO = "Inicio de sesión exitoso";
    private static final String ERROR_CREDENCIALES = "Credenciales incorrectas";
    private static final String ERROR_AUTENTICACION = "Error en la autenticación";

    @Override
    public RespuestaGeneralDTO loginContact(LoginContactDTO loginContactDTO) {
        RespuestaGeneralDTO respuestaGeneralDTO = new RespuestaGeneralDTO();
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginContactDTO.getEmailAddress(),
                            loginContactDTO.getPassword()
                    )
            );
            if (authentication.isAuthenticated()) {
                String token = jwtService.getToken((UserDetails) authentication.getPrincipal());

                respuestaGeneralDTO.setData(AuthResponseDTO.builder().token(token).build());
                respuestaGeneralDTO.setMessage(INICIO_EXITOSO);
                respuestaGeneralDTO.setStatus(HttpStatus.OK);
            } else {
                respuestaGeneralDTO.setMessage(ERROR_CREDENCIALES);
                respuestaGeneralDTO.setStatus(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            log.error(ERROR_AUTENTICACION, e);
            respuestaGeneralDTO.setMessage(ERROR_CREDENCIALES);
            respuestaGeneralDTO.setStatus(HttpStatus.UNAUTHORIZED);
        }
        return respuestaGeneralDTO;
    }

}
