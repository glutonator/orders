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

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime  relationCreationDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime relationModificationDate;

    private boolean relationStatus;

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
        this.relationCreationDate = relationCreationDate;
        this.relationStatus = relationStatus;
    }

    public Booking(Long eventID, Long ticketID, LocalDateTime relationCreationDate, LocalDateTime relationModificationDate, boolean relationStatus, OrderObjcet orderObjcet) {
        this.eventID = eventID;
        this.ticketID = ticketID;
        this.relationCreationDate = relationCreationDate;
        this.relationModificationDate = relationModificationDate;
        this.relationStatus = relationStatus;
        this.orderObjcet = orderObjcet;
    }

    public Long getRelationID() {
        return relationID;
    }

    public void setRelationID(Long relationID) {
        this.relationID = relationID;
    }

    public Long getEventID() {
        return eventID;
    }

    public void setEventID(Long eventID) {
        this.eventID = eventID;
    }

    public Long getTicketID() {
        return ticketID;
    }

    public void setTicketID(Long ticketID) {
        this.ticketID = ticketID;
    }

    public LocalDateTime getRelationCreationDate() {
        return relationCreationDate;
    }

    public void setRelationCreationDate(LocalDateTime relationCreationDate) {
        this.relationCreationDate = relationCreationDate;
    }

    public LocalDateTime getRelationModificationDate() {
        return relationModificationDate;
    }

    public void setRelationModificationDate(LocalDateTime relationModificationDate) {
        this.relationModificationDate = relationModificationDate;
    }

    public boolean isRelationStatus() {
        return relationStatus;
    }

    public void setRelationStatus(boolean relationStatus) {
        this.relationStatus = relationStatus;
    }

    public OrderObjcet getOrderObjcet() {
        return orderObjcet;
    }

    public void setOrderObjcet(OrderObjcet orderObjcet) {
        this.orderObjcet = orderObjcet;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "relationID=" + relationID +
                ", eventID=" + eventID +
                ", ticketID=" + ticketID +
                ", relationCreationDate=" + relationCreationDate +
                ", relationModificationDate=" + relationModificationDate +
                ", relationStatus=" + relationStatus +
                ", orderObjcet=" + orderObjcet +
                '}';
    }
}
