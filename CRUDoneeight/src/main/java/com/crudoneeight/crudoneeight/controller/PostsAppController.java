package com.crudoneeight.crudoneeight.controller;

import com.crudoneeight.crudoneeight.model.PostsApp;
import com.crudoneeight.crudoneeight.repository.IRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,
        RequestMethod.DELETE})
@RequestMapping("/app/posts")
public class PostsAppController {
    @Autowired
    private IRepo repo;

    @PostMapping
    public ResponseEntity<PostsApp> create(@Validated @RequestBody PostsApp p) {
        // Verificar si el _id ya existe en la base de datos
        Optional<PostsApp> existingPost = repo.findById(p.get_id());

        if (existingPost.isPresent()) {
            // El _id ya existe, responder con un código de estado 409 (CONFLICT) o algún otro código apropiado
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            // El _id no existe, proceder con la inserción
            PostsApp savedPost = repo.save(p);
            // Responder con el post guardado y el código de estado 201 (CREATED)
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
        }
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PostsApp>> readAll() {
        List<PostsApp> allPosts = repo.findAll();
        if (allPosts.isEmpty()) {
            // No hay registros en la base de datos, responder con 404 (NOT_FOUND)
            return ResponseEntity.notFound().build();
        } else {
            // Responder con la lista de registros y el código de estado 200 (OK)
            return ResponseEntity.ok(allPosts);
        }
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PostsApp> findById(@PathVariable String id) {
        Optional<PostsApp> post = repo.findById(id);
        // El registro existe, responder con el objeto y el código de estado 200 (OK). El registro no se encontró en la base de datos, responder con 404 (NOT_FOUND)
        return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostsApp> update(@PathVariable String id, @Validated @RequestBody PostsApp p) {
        if (repo.existsById(id)) {
            // El registro existe, proceder con la actualización
            PostsApp updatedPost = repo.save(p);
            // Responder con el objeto actualizado y el código de estado 200 (OK)
            return ResponseEntity.ok(updatedPost);
        } else {
            // El registro no se encontró en la base de datos, responder con 404 (NOT_FOUND)
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (repo.existsById(id)) {
            // El registro existe, proceder con la eliminación
            repo.deleteById(id);
            // Responder con 204 (NO_CONTENT)
            return ResponseEntity.noContent().build();
        } else {
            // El registro no se encontró en la base de datos, responder con 404 (NOT_FOUND)
            return ResponseEntity.notFound().build();
        }
    }
}
