package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TokenDto;
import br.com.alura.forum.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Profile;
import br.com.alura.forum.controller.form.LoginForm;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Profile("prod")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form){
        return authService.autenticarUsuario(form);

    }
}
