package co.uni.contact.management.system.controller;

import co.uni.contact.management.system.dto.LoginContactDTO;
import co.uni.contact.management.system.dto.RespuestaGeneralDTO;
import co.uni.contact.management.system.service.ILoginContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class LoginController {

    /**
     * Servicio para la lógica de inicio de sesión de contactos.
     */
    private final ILoginContactService iLoginContactService;

    @PostMapping("/login")
    public ResponseEntity<RespuestaGeneralDTO> loginContact(@RequestBody LoginContactDTO loginContactDTO) {
        RespuestaGeneralDTO respuestaGeneralDTO = iLoginContactService.loginContact(loginContactDTO);
        return ResponseEntity.status(respuestaGeneralDTO.getStatus()).body(respuestaGeneralDTO);
    }

}
