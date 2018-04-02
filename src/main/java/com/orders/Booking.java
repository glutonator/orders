package com.orders;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Booking {

    @Id
    @SequenceGenerator(name="SEQ_GEN_BOOKING", sequenceName="SEQ_GEN_BOOKING", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="SEQ_GEN_BOOKING")
    private Long IdBooked;

    private Long IdUser;

    private Long IdEvent;

    private Long IdTicket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="tmp",nullable = false)
    private TicketList ticketList;


}
