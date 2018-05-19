package com.orders;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity // This tells Hibernate to make a table out of this class
public class OrderObjcet {
    @Id
    @SequenceGenerator(name = "SEQ_GEN_ORDER", sequenceName = "SEQ_GEN_ORDER", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN_ORDER")
    private Long orderID;

    private Long userID;

    private Long paymentOrder;

    private boolean status; // 1 - reserved, 0 - canceled

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "orderObjcet")
//    private Set<Booking> bookings = new HashSet<>();
    private List<Booking> bookings = new ArrayList<>();

    public OrderObjcet() {
    }

    public OrderObjcet(Long userID) {
        this.userID = userID;
    }

    public OrderObjcet(Long userID, Long paymentOrder) {
        this.userID = userID;
        this.paymentOrder = paymentOrder;
    }

//    public OrderObjcet(Long userID, Long paymentOrder, Set<Booking> bookings) {
//        this.userID = userID;
//        this.paymentOrder = paymentOrder;
//        this.bookings = bookings;
//    }

    public OrderObjcet(Long userID, Long paymentOrder, List<Booking> bookings) {
        this.userID = userID;
        this.paymentOrder = paymentOrder;
        this.bookings = bookings;
    }


//    public OrderObjcet(Long userID, Long paymentOrder, boolean status, Set<Booking> bookings) {
//        this.userID = userID;
//        this.paymentOrder = paymentOrder;
//        this.status = status;
//        this.bookings = bookings;
//    }

    public OrderObjcet(Long userID, Long paymentOrder, boolean status, List<Booking> bookings) {
        this.userID = userID;
        this.paymentOrder = paymentOrder;
        this.status = status;
        this.bookings = bookings;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        orderID = orderID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getPaymentOrder() {
        return paymentOrder;
    }

    public void setPaymentOrder(Long paymentOrder) {
        this.paymentOrder = paymentOrder;
    }

//    public Set<Booking> getBookings() {
//        return bookings;
//    }
//
//    public void setBookings(Set<Booking> bookings) {
//        this.bookings = bookings;
//    }


    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderObjcet{" +
                "orderID=" + orderID +
                ", userID=" + userID +
                ", paymentOrder=" + paymentOrder +
                ", status=" + status +
                ", bookings=" + bookings +
                '}';
    }
}
