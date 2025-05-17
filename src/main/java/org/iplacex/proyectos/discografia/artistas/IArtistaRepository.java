package org.iplacex.proyectos.discografia.artistas;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


//repositorio para acceder a la colección de artistas en mongodb.
@Repository
public interface IArtistaRepository extends MongoRepository<Artista, String> 
{
}