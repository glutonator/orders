package com.orders;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class Booking {

    @Id
    @SequenceGenerator(name="SEQ_GEN_BOOKING", sequenceName="SEQ_GEN_BOOKING", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="SEQ_GEN_BOOKING")
    private Long IdBooked;

    private Long IdUser;

    private Long IdEvent;

    private Long IdTicket;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "bookings")
    private Set<OrderObjcet> orderObjcets = new HashSet<>();

    public Booking(Long idUser, Long idEvent, Long idTicket) {
        IdUser = idUser;
        IdEvent = idEvent;
        IdTicket = idTicket;
    }

    public Long getIdBooked() {
        return IdBooked;
    }

    public void setIdBooked(Long idBooked) {
        IdBooked = idBooked;
    }

    public Long getIdUser() {
        return IdUser;
    }

    public void setIdUser(Long idUser) {
        IdUser = idUser;
    }

    public Long getIdEvent() {
        return IdEvent;
    }

    public void setIdEvent(Long idEvent) {
        IdEvent = idEvent;
    }

    public Long getIdTicket() {
        return IdTicket;
    }

    public void setIdTicket(Long idTicket) {
        IdTicket = idTicket;
    }

    public Set<OrderObjcet> getOrderObjcets() {
        return orderObjcets;
    }

    public void setOrderObjcets(Set<OrderObjcet> orderObjcets) {
        this.orderObjcets = orderObjcets;
    }
}
