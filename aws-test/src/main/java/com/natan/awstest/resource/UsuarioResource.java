package com.natan.awstest.resource;

import com.natan.awstest.model.Usuario;
import com.natan.awstest.model.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class UsuarioResource {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String getDefault() {
        return "Hello, baby!";
    }

    @GetMapping("usuarios")
    public List<Usuario> list() {
        return usuarioRepository.findAll();
    }
}
