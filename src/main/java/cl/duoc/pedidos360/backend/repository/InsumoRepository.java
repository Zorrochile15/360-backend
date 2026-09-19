package cl.duoc.pedidos360.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.pedidos360.backend.entity.Insumo;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long> {
}