package cl.duoc.pedidos360.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false, length = 50)
    private String estado; // Ej: PENDIENTE, APROBADO, EN_DESPACHO, ENTREGADO

    // Relación: Muchos pedidos pertenecen a un Local
    @ManyToOne
    @JoinColumn(name = "local_id", nullable = false)
    private Local local;
}