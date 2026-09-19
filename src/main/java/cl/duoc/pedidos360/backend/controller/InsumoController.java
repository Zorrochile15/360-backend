package cl.duoc.pedidos360.backend.controller;

import java.util.List;
import java.util.Optional;

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

import cl.duoc.pedidos360.backend.entity.Insumo;
import cl.duoc.pedidos360.backend.repository.InsumoRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class InsumoController {

    @Autowired
    private InsumoRepository insumoRepository;

    @GetMapping("/public/insumos")
    public ResponseEntity<List<Insumo>> getAllInsumos() {
        List<Insumo> insumos = insumoRepository.findAll();
        return new ResponseEntity<>(insumos, HttpStatus.OK);
    }

    @GetMapping("/public/insumos/{id}")
    public ResponseEntity<Insumo> getInsumoById(@PathVariable Long id) {
        Optional<Insumo> insumo = insumoRepository.findById(id);
        return insumo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/admin/insumos")
    public ResponseEntity<Insumo> createInsumo(@RequestBody Insumo insumo) {
        Insumo savedInsumo = insumoRepository.save(insumo);
        return new ResponseEntity<>(savedInsumo, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/insumos/{id}")
    public ResponseEntity<HttpStatus> deleteInsumo(@PathVariable Long id) {
        try {
            insumoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}