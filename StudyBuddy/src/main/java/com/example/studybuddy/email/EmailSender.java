package com.example.studybuddy.email;

/**
 * Interface na posílání emailů
 * */
public interface EmailSender {
    void send(String to, String email);
}
