package com.orders;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class Booking {

    @Id
    @SequenceGenerator(name="SEQ_GEN_BOOKING", sequenceName="SEQ_GEN_BOOKING", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="SEQ_GEN_BOOKING")
    private Long relationID;

    private Long eventID;

    private Long ticketID;

//    private CustomDate RelationCreationDate;
//
//    private CustomDate RelationModificationDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime  RelationCreationDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime RelationModificationDate;

    private boolean RelationStatus;

////fetch = FetchType.LAZY
//    @ManyToMany(fetch = FetchType.EAGER,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            },
//            mappedBy = "bookings")
//    private Set<OrderObjcet> orderObjcets = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdOrder", nullable = false)
    @JsonBackReference
    private OrderObjcet orderObjcet;

    public Booking() {
    }

    public Booking(Long eventID, Long ticketID) {
        this.eventID = eventID;
        this.ticketID = ticketID;
    }


    public Booking(Long eventID, Long ticketID, LocalDateTime relationCreationDate, boolean relationStatus) {
        this.eventID = eventID;
        this.ticketID = ticketID;
        RelationCreationDate = relationCreationDate;
        RelationStatus = relationStatus;
    }

    public Long getRelationID() {
        return relationID;
    }

    public void setRelationID(Long relationID) {
        relationID = relationID;
    }

    public Long getEventID() {
        return eventID;
    }

    public void setEventID(Long eventID) {
        eventID = eventID;
    }

    public Long getTicketID() {
        return ticketID;
    }

    public void setTicketID(Long ticketID) {
        ticketID = ticketID;
    }

    public OrderObjcet getOrderObjcet() {
        return orderObjcet;
    }

    public void setOrderObjcet(OrderObjcet orderObjcet) {
        this.orderObjcet = orderObjcet;
    }

    public LocalDateTime getRelationCreationDate() {
        return RelationCreationDate;
    }

    public void setRelationCreationDate(LocalDateTime relationCreationDate) {
        RelationCreationDate = relationCreationDate;
    }

    public LocalDateTime getRelationModificationDate() {
        return RelationModificationDate;
    }

    public void setRelationModificationDate(LocalDateTime relationModificationDate) {
        RelationModificationDate = relationModificationDate;
    }

    public boolean isRelationStatus() {
        return RelationStatus;
    }

    public void setRelationStatus(boolean relationStatus) {
        RelationStatus = relationStatus;
    }

    //    public Set<OrderObjcet> getOrderObjcets() {
//        return orderObjcets;
//    }
//
//    public void setOrderObjcets(Set<OrderObjcet> orderObjcets) {
//        this.orderObjcets = orderObjcets;
//    }


    @Override
    public String toString() {
        return "Booking{" +
                "relationID=" + relationID +
                ", eventID=" + eventID +
                ", ticketID=" + ticketID +
                ", RelationCreationDate=" + RelationCreationDate +
                ", RelationModificationDate=" + RelationModificationDate +
                ", RelationStatus=" + RelationStatus +
                ", orderObjcet=" + orderObjcet +
                '}';
    }
}
