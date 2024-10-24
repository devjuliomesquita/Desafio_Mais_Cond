package com.juliomesquita.preparation.infrastructure.api.commom.exceptions;

import java.util.Map;

public record CustomErrorResponse(Map<String, String> errors) {
}
