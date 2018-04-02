package com.orders;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity //@IdClass(TicketListId.class)// This tells Hibernate to make a table out of this class
public class TicketList {

    @Id
    private Long IdOrder;
    //@Id
    //private Long IdBooked;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Booking> bookingSet=new HashSet<>();
//mappedBy = "TicketList"
}
