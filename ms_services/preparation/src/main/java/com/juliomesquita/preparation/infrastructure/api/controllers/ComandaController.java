package com.juliomesquita.preparation.infrastructure.api.controllers;

import com.juliomesquita.preparation.application.usecase.close.CloseComandaCommand;
import com.juliomesquita.preparation.application.usecase.close.CloseComandaUseCase;
import com.juliomesquita.preparation.application.usecase.retrive.getbyid.GetComandaByIdUseCase;
import com.juliomesquita.preparation.application.usecase.retrive.list.ListComandasUseCase;
import com.juliomesquita.preparation.infrastructure.api.ComandaAPI;
import com.juliomesquita.preparation.infrastructure.api.models.ComandaResponse;
import com.juliomesquita.preparation.infrastructure.api.models.ListComandasResponse;
import com.juliomesquita.preparation.infrastructure.api.presenter.ComandaPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class ComandaController implements ComandaAPI {
    private final GetComandaByIdUseCase getComandaByIdUseCase;
    private final ListComandasUseCase listComandasUseCase;
    private final CloseComandaUseCase closeComandaUseCase;

    public ComandaController(
            final GetComandaByIdUseCase getComandaByIdUseCase,
            final ListComandasUseCase listComandasUseCase,
            final CloseComandaUseCase closeComandaUseCase) {
        this.getComandaByIdUseCase = Objects.requireNonNull(getComandaByIdUseCase);
        this.listComandasUseCase = Objects.requireNonNull(listComandasUseCase);
        this.closeComandaUseCase = Objects.requireNonNull(closeComandaUseCase);
    }

    @Override
    public ResponseEntity<List<ListComandasResponse>> listComandas() {
        final List<ListComandasResponse> response = this.listComandasUseCase.execute()
                .stream()
                .map(ComandaPresenter.presenterList)
                .toList();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ComandaResponse> getComandaById(final String id) {
        final ComandaResponse response = ComandaPresenter.presenterSimple
                .compose(this.getComandaByIdUseCase::execute)
                .apply(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> closeComandaById(final String id) {
        this.closeComandaUseCase.execute(new CloseComandaCommand(id));
        return ResponseEntity.ok().build();
    }
}
