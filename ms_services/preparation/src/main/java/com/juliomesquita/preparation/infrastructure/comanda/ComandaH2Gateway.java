package com.juliomesquita.preparation.infrastructure.comanda;

import com.juliomesquita.preparation.domain.ComandaGateway;
import com.juliomesquita.preparation.domain.entities.Comanda;
import com.juliomesquita.preparation.infrastructure.comanda.persistence.ComandaEntity;
import com.juliomesquita.preparation.infrastructure.comanda.persistence.ComandaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ComandaH2Gateway implements ComandaGateway {
    private final ComandaRepository comandaRepository;

    public ComandaH2Gateway(final ComandaRepository comandaRepository) {
        this.comandaRepository = Objects.requireNonNull(comandaRepository);
    }

    @Override
    public String save(final Comanda aComanda) {
        final ComandaEntity anEntity = ComandaEntity.from(aComanda);
        return this.comandaRepository.save(anEntity).getId();
    }

    @Override
    public List<Comanda> getAll() {
        return this.comandaRepository.findAll()
                .stream()
                .map(ComandaEntity::toAggregate)
                .toList();
    }

    @Override
    public Optional<Comanda> getById(String anId) {
        return this.comandaRepository.findById(anId)
                .map(ComandaEntity::toAggregate);
    }
}
