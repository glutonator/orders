package com.orders;

public class StringRES {
    private boolean status;

    public StringRES() {
    }

    public StringRES(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StringRES{" +
                "status=" + status +
                '}';
    }
}
