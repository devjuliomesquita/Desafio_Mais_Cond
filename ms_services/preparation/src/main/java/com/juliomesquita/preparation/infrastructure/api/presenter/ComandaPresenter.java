package com.juliomesquita.preparation.infrastructure.api.presenter;

import com.juliomesquita.preparation.application.usecase.retrive.getbyid.GetComandaByIdOutput;
import com.juliomesquita.preparation.application.usecase.retrive.list.ListComandasOutput;
import com.juliomesquita.preparation.infrastructure.api.models.ComandaResponse;
import com.juliomesquita.preparation.infrastructure.api.models.ListComandasResponse;

import java.util.function.Function;

public interface ComandaPresenter {
    Function<GetComandaByIdOutput, ComandaResponse> presenterSimple =
            output -> new ComandaResponse(
                    output.id(),
                    output.order(),
                    output.closed(),
                    output.createdAt(),
                    output.updatedAt()
            );

    Function<ListComandasOutput, ListComandasResponse> presenterList =
            output -> new ListComandasResponse(
                    output.id(),
                    output.closed(),
                    output.createdAt()
            );
}
