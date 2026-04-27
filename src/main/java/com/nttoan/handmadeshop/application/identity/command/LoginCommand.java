package com.nttoan.handmadeshop.application.identity.command;

public record LoginCommand(
        String email,
        String password) {
}
