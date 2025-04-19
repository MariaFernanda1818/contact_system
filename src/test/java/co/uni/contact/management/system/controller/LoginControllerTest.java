package co.uni.contact.management.system.controller;

import co.uni.contact.management.system.dto.LoginContactDTO;
import co.uni.contact.management.system.dto.RespuestaGeneralDTO;
import co.uni.contact.management.system.service.ILoginContactService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Mock
    private ILoginContactService iLoginContactService;

    @Test
    void loginExitoso() {
        LoginContactDTO loginContactDTO = new LoginContactDTO();
        loginContactDTO.setEmailAddress("contacto@gmail.com");
        loginContactDTO.setPassword("12345");
        RespuestaGeneralDTO respuestaGeneralDTO = new RespuestaGeneralDTO();
        respuestaGeneralDTO.setStatus(HttpStatus.OK);
        when(iLoginContactService.loginContact(any())).thenReturn(respuestaGeneralDTO);
        ResponseEntity<RespuestaGeneralDTO> response = loginController.loginContact(loginContactDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
