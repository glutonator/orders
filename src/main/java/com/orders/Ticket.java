package com.orders;

public class Ticket {

    private Long id;

    private String status; // AVAILABLE, OCCUPIED, CANCELED


    public Ticket() {
    }

    public Ticket(Long id, String status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
