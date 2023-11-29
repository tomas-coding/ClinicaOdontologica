package com.clinicaodontologica.Clinica.Odontologica.security;

import com.clinicaodontologica.Clinica.Odontologica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(usuarioService);
    return provider;


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(daoAuthenticationProvider());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
     http
             .csrf().disable()
             .authorizeRequests()
             // .antMatchers("/get_pacientes.html").hasAnyRole("USER,ADMIN")
                 .antMatchers("/post_odontologos.html").hasRole("ADMIN") // USER
                 .antMatchers("/post_pacientes.html").hasRole("ADMIN")
                 .antMatchers("/post_turnos.html").hasRole("ADMIN")
                 .antMatchers("/get_odontologos.html").hasRole("ADMIN")
                 .antMatchers("/get_pacientes.html").hasRole("ADMIN")
                 .antMatchers("/get_turnos.html").hasAnyRole("ADMIN","USER")
                 .antMatchers("/index.html").hasAnyRole("ADMIN","USER")
                // .antMatchers("/get_turnos.html").hasRole("USER") // <- al parecer, aparece como una soobrescritura de los roles ( user REEMPLAZA admin? )


                 .anyRequest()
             .authenticated()
             .and()
             .formLogin()
             .and()
             .logout();
    }
}
