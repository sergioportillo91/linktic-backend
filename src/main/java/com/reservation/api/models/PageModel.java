package com.reservation.api.models;

public record PageModel<T>(T data, Long total) {
}

