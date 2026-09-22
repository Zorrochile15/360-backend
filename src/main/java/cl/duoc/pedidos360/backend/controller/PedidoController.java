package cl.duoc.pedidos360.backend.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.pedidos360.backend.entity.Pedido;
import cl.duoc.pedidos360.backend.repository.PedidoRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("/admin/pedidos")
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        return new ResponseEntity<>(
            pedidoRepository.findAll(),
            HttpStatus.OK
        );
    }

    @GetMapping("/locales/pedidos")
    public ResponseEntity<List<Pedido>> getPedidosByLocal(
        @RequestParam Long localId
    ) {
        return new ResponseEntity<>(
            pedidoRepository.findByLocalId(localId),
            HttpStatus.OK
        );
    }

    @PostMapping("/locales/pedidos")
    public ResponseEntity<Pedido> createPedido(
        @RequestBody Pedido pedido
    ) {
        pedido.setFechaCreacion(LocalDateTime.now());
        pedido.setEstado("PENDIENTE");

        return new ResponseEntity<>(
            pedidoRepository.save(pedido),
            HttpStatus.CREATED
        );
    }
}
