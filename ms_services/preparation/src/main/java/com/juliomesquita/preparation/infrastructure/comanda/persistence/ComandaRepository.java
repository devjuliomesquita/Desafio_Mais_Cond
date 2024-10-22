package com.juliomesquita.preparation.infrastructure.comanda.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComandaRepository extends JpaRepository<ComandaEntity, String> {
}
