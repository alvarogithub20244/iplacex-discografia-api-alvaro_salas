package org.iplacex.proyectos.discografia.artistas;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//coleccion artistas en base de datos
@Document(collection = "artistas")

//atributos de artista
public class Artista 
{
    @Id
    public String _id;
    public String nombre;
    public List<String> estilos;
    public int anioFundacion;
    public boolean estaActivo;
    
    //construictor vacio
    public Artista() 
    {}
    
    //constructor con todos los campos
    public Artista(String _id, String nombre, List<String> estilos, int anioFundacion, boolean estaActivo) 
    {
        this._id = _id;
        this.nombre = nombre;
        this.estilos = estilos;
        this.anioFundacion = anioFundacion;
        this.estaActivo = estaActivo;
    }
    
}