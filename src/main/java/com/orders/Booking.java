package com.orders;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Booking {

    @Id
    @SequenceGenerator(name="SEQ_GEN_BOOKING", sequenceName="SEQ_GEN_BOOKING", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="SEQ_GEN_BOOKING")
    private Integer IdBooked;

    private Integer IdUser;

    private Integer IdEvent;

    private Integer IdTicket;
}
