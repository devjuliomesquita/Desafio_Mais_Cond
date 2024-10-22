package com.juliomesquita.preparation.domain;

import com.juliomesquita.preparation.domain.entities.Comanda;

import java.util.List;
import java.util.Optional;

public interface ComandaGateway {
    String save(Comanda aComanda);

    List<Comanda> getAll();

    Optional<Comanda> getById(String anId);
}
