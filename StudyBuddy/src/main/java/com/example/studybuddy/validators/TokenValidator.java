package com.example.studybuddy.validators;

import com.example.studybuddy.registration.token.ConfirmationToken;
import com.example.studybuddy.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Validátor tokenu
 * */
@Service
@AllArgsConstructor
public class TokenValidator {

    private final ConfirmationTokenService confirmationTokenService;

    public void test(String token) {

        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(() -> new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedat() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresat();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }
    }

}
