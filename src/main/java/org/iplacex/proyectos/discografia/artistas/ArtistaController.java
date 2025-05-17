package org.iplacex.proyectos.discografia.artistas;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//controlador rest para la gestion de artistas
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ArtistaController 
{
    @Autowired
    private IArtistaRepository artistaRepository;
    
    //crear un nuevo artista
    @PostMapping(value = "/artista", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registrarArtista(@RequestBody Artista artista) 
    {
        Artista nuevoArtista = artistaRepository.save(artista);
        return new ResponseEntity<>(nuevoArtista, HttpStatus.CREATED);
    }

    //obtener todos los artistas
    @GetMapping(value = "/artistas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Artista>> listarArtistas() 
    {
        List<Artista> artistas = artistaRepository.findAll();
        return new ResponseEntity<>(artistas, HttpStatus.OK);
    }
    
    //obtener un artista por id
    @GetMapping(value = "/artista/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> buscarArtistaPorId(@PathVariable String id) 
    {
        Optional<Artista> artista = artistaRepository.findById(id);
        if (artista.isPresent()) 
        {
            return new ResponseEntity<>(artista.get(), HttpStatus.OK);
        } 
        else 
        {
            return new ResponseEntity<>("no se encuentra artista con ese id: " + id, HttpStatus.NOT_FOUND);
        }
    }
    
    //actualizar un artista existente
    @PutMapping(value = "/artista/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> actualizarArtista(@PathVariable String id, @RequestBody Artista artistaActualizado) 
    {
        if (artistaRepository.existsById(id)) 
        {
            artistaActualizado._id = id;
            Artista artistaGuardado = artistaRepository.save(artistaActualizado);
            return new ResponseEntity<>(artistaGuardado, HttpStatus.OK);
        } 
        else 
        {
            return new ResponseEntity<>("no se encuentra artista con ese id: " + id, HttpStatus.NOT_FOUND);
        }
    }
    
    //eliminar un artista
    @DeleteMapping(value = "/artista/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> eliminarArtista(@PathVariable String id) 
    {
        if (artistaRepository.existsById(id)) 
        {
            artistaRepository.deleteById(id);
            return new ResponseEntity<>("se elimina artista con id: " + id, HttpStatus.OK);
        } 
        else 
        {
            return new ResponseEntity<>("no se encuentra artista con ese id: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
