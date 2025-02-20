package net.weg.mi75.repository;

import net.weg.mi75.models.entity.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaRespository extends JpaRepository<Transferencia, String> {
}
