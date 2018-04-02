package com.orders;


import java.io.Serializable;

public class TicketListId implements Serializable {
    private Long IdOrder;
    private Long IdBooked;

    public Long getIdOrder() {
        return IdOrder;
    }

    public void setIdOrder(Long idOrder) {
        IdOrder = idOrder;
    }

    public Long getIdBooked() {
        return IdBooked;
    }

    public void setIdBooked(Long idBooked) {
        IdBooked = idBooked;
    }
}
