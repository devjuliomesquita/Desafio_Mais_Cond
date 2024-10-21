package com.juliomesquita.menu.infrastruture.table;

import com.juliomesquita.menu.domain.table.TableRestaurant;
import com.juliomesquita.menu.domain.table.TableRestaurantGateway;
import com.juliomesquita.menu.infrastruture.table.persistence.TableEntity;
import com.juliomesquita.menu.infrastruture.table.persistence.TableRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TableH2RestaurantGateway implements TableRestaurantGateway {
    private final TableRepository tableRepository;

    public TableH2RestaurantGateway(final TableRepository tableRepository) {
        this.tableRepository = Objects.requireNonNull(tableRepository);
    }

    @Override
    public String save(TableRestaurant aTableRestaurant) {
        final TableEntity aEntity = TableEntity.from(aTableRestaurant);
        final TableEntity aEntitySaved = this.tableRepository.save(aEntity);
        return aEntitySaved.getId();
    }

    @Override
    public Optional<TableRestaurant> findById(String anId) {
        return this.tableRepository.findById(anId)
                .map(TableEntity::toAggregate);
    }

    @Override
    public List<TableRestaurant> findAll() {
        return this.tableRepository
                .findAll()
                .stream()
                .map(TableEntity::toAggregate)
                .toList();
    }
}
