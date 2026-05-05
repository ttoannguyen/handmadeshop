package com.nttoan.handmadeshop.application.identity.command;

public record LoginCommand(
        String username,
        String password) {
}
