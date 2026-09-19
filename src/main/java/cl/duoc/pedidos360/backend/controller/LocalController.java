package cl.duoc.pedidos360.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.pedidos360.backend.entity.Local;
import cl.duoc.pedidos360.backend.repository.LocalRepository;

@RestController
@RequestMapping("/api/admin/locales")
@CrossOrigin(origins = "*")
public class LocalController {

    @Autowired
    private LocalRepository localRepository;

    @GetMapping
    public ResponseEntity<List<Local>> getAllLocales() {
        return new ResponseEntity<>(localRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Local> createLocal(@RequestBody Local local) {
        return new ResponseEntity<>(localRepository.save(local), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteLocal(@PathVariable Long id) {
        try {
            localRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}