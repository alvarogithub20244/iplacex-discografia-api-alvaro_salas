package org.iplacex.proyectos.discografia.discos;

import java.util.List;
import java.util.Optional;

import org.iplacex.proyectos.discografia.artistas.IArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//controlador rest para la gestion de discos
@RestController
@CrossOrigin
@RequestMapping("/api")
public class DiscoController 
{
    @Autowired
    private IDiscoRepository discoRepository;
    
    @Autowired
    private IArtistaRepository artistaRepository;
    
    //crear un nuevo disco verificando que el artista exista
    @PostMapping(value = "/disco", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> crearDisco(@RequestBody Disco disco) 
    {
        if (artistaRepository.existsById(disco.idArtista)) 
        {
            Disco nuevoDisco = discoRepository.save(disco);
            return new ResponseEntity<>(nuevoDisco, HttpStatus.CREATED);
        } 
        else 
        {
            return new ResponseEntity<>("no se puede crear el disco porque el artista con id: " + disco.idArtista + " no existe", HttpStatus.BAD_REQUEST);
        }
    }
    
    //obtener todos los discos
    @GetMapping(value = "/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disco>> listarDiscos() 
    {
        List<Disco> discos = discoRepository.findAll();
        return new ResponseEntity<>(discos, HttpStatus.OK);
    }
    
    //obtener un disco por su id
    @GetMapping(value = "/disco/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> buscarDiscoPorId(@PathVariable String id) 
    {
        Optional<Disco> disco = discoRepository.findById(id);
        if (disco.isPresent()) 
        {
            return new ResponseEntity<>(disco.get(), HttpStatus.OK);
        } 
        else 
        {
            return new ResponseEntity<>("no se encuentra disco con id: " + id, HttpStatus.NOT_FOUND);
        }
    }
    
    //obtener todos los discos de un artista específico
    @GetMapping(value = "/artista/{id}/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disco>> listarDiscosPorArtista(@PathVariable String id) 
    {
        List<Disco> discos = discoRepository.findDiscosByIdArtista(id);
        return new ResponseEntity<>(discos, HttpStatus.OK);
    }
}
