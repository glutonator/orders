package com.orders;

import javax.persistence.*;


@Entity // This tells Hibernate to make a table out of this class
public class TicketList {

    @Id
    @SequenceGenerator(name="SEQ_GEN_ORDER", sequenceName="SEQ_GEN_ORDER", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="SEQ_GEN_ORDER")
    private Integer IdOrder;

    private Integer IdBooked;

}
