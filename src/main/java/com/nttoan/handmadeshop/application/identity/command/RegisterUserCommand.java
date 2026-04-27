package com.nttoan.handmadeshop.application.identity.command;

import java.time.LocalDate;

public record RegisterUserCommand(
        String username,
        String email,
        String password,
        String fullName,
        LocalDate dateOfBirth) {

}
