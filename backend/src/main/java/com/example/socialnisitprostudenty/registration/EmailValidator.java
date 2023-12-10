package com.example.socialnisitprostudenty.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;


/**
 * Email validátor
 * */
@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        // TODO: Regex pro ověření účtu
        return true;
    }
}
