package co.uni.contact.management.system.service.Impl;

import co.uni.contact.management.system.dto.AuthResponseDTO;
import co.uni.contact.management.system.dto.LoginContactDTO;
import co.uni.contact.management.system.dto.RespuestaGeneralDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginContactServiceTest {

    @InjectMocks
    private LoginContactService loginContactService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    private static final String INICIO_EXITOSO = "Inicio de sesión exitoso";
    private static final String ERROR_CREDENCIALES = "Credenciales incorrectas";

    @Test
    void loginExitoso() {
        // Arrange
        String email = "contacto@test.com";
        String password = "password123";
        String token = "mocked-token";
        LoginContactDTO loginContactDTO = new LoginContactDTO();
        loginContactDTO.setEmailAddress(email);
        loginContactDTO.setPassword(password);

        UserDetails userDetailsMock = mock(UserDetails.class);
        Authentication authenticationMock = mock(Authentication.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authenticationMock);
        when(authenticationMock.isAuthenticated()).thenReturn(true);
        when(authenticationMock.getPrincipal()).thenReturn(userDetailsMock);
        when(jwtService.getToken(userDetailsMock)).thenReturn(token);

        RespuestaGeneralDTO respuesta = loginContactService.loginContact(loginContactDTO);

        assertEquals(HttpStatus.OK, respuesta.getStatus());
        assertEquals(INICIO_EXITOSO, respuesta.getMessage());
        assertNotNull(respuesta.getData());

        AuthResponseDTO authResponse = (AuthResponseDTO) respuesta.getData();
        assertEquals(token, authResponse.getToken());

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService, times(1)).getToken(userDetailsMock);
    }

    @Test
    void loginFallido() {
        // Arrange
        String email = "contacto@test.com";
        String password = "wrongPassword";
        LoginContactDTO loginContactDTO = new LoginContactDTO();
        loginContactDTO.setEmailAddress(email);
        loginContactDTO.setPassword(password);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new RuntimeException("Error de autenticación"));

        RespuestaGeneralDTO respuesta = loginContactService.loginContact(loginContactDTO);

        assertEquals(HttpStatus.UNAUTHORIZED, respuesta.getStatus());
        assertEquals(ERROR_CREDENCIALES, respuesta.getMessage());
        assertNull(respuesta.getData());

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verifyNoInteractions(jwtService);
    }
}
