package org.iplacex.proyectos.discografia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//clase principal que inicia la aplicacion spring.
@SpringBootApplication
public class ApiApplication 
{
    //metodo principal que arranca la aplicacion spring.
    public static void main(String[] args) 
    {
        SpringApplication.run(ApiApplication.class, args);
    }
}