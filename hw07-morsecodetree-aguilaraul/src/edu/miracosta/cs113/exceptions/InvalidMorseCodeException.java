package edu.miracosta.cs113.exceptions;

public class InvalidMorseCodeException extends Exception {
    public InvalidMorseCodeException(String errorMessage) {
        super(errorMessage);
    }
}
