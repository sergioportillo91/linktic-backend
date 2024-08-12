package com.reservation.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {

    @JsonProperty("registros")
    private T data;

    @JsonProperty("cantidadTotal")
    private Long total;

}