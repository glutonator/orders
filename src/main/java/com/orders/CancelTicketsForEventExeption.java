package com.orders;

public class CancelTicketsForEventExeption extends RuntimeException {
    public CancelTicketsForEventExeption() {
    }

    public CancelTicketsForEventExeption(String message) {
        super(message);
    }

}
