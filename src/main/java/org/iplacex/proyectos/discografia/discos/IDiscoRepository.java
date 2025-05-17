package org.iplacex.proyectos.discografia.discos;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

//repositorio para acceder a la coleccion de discos en mongodb.
@Repository
public interface IDiscoRepository extends MongoRepository<Disco, String> 
{
    //busca todos los discos asociados a un artista especifica metodo @Query buscar disco por id artista.
    @Query("{ 'idArtista': ?0 }")
    List<Disco> findDiscosByIdArtista(String idArtista) throws Error;
}