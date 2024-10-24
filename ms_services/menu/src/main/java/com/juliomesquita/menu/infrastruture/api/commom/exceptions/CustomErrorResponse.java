package com.juliomesquita.menu.infrastruture.api.commom.exceptions;

import java.util.Map;

public record CustomErrorResponse(Map<String, String> errors) {
}
