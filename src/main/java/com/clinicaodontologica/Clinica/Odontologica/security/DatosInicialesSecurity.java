package com.clinicaodontologica.Clinica.Odontologica.security;

import com.clinicaodontologica.Clinica.Odontologica.model.Usuario;
import com.clinicaodontologica.Clinica.Odontologica.model.UsuarioRole;
import com.clinicaodontologica.Clinica.Odontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosInicialesSecurity implements ApplicationRunner {
    @Autowired
    UsuarioRepository usuarioRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        //crear un usuario como si fuese real

        BCryptPasswordEncoder cifrador= new BCryptPasswordEncoder();
        String passSinCifrar= "digital";
        String passCifrado= cifrador.encode(passSinCifrar);
        System.out.println("password: "+passCifrado);
        Usuario usuarioInsertar= new Usuario("Jorgito","jpereyra00","jorge.pereyra@digitalhouse.com",passCifrado, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuarioInsertar);

        Usuario usuarioUser= new Usuario("Tomi","tomilovato","tomi@gmail.com",passCifrado, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuarioUser);

    }
}
