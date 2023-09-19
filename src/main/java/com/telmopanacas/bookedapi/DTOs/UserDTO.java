package com.telmopanacas.bookedapi.DTOs;

public record UserDTO(
        Integer id,
        String displayName,
        String email
) {
}
