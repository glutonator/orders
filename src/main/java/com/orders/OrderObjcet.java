package com.orders;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity // This tells Hibernate to make a table out of this class
public class OrderObjcet {
    @Id
    @SequenceGenerator(name = "SEQ_GEN_ORDER", sequenceName = "SEQ_GEN_ORDER", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN_ORDER")
    private Long orderID;

    private Long userID;

    private Long paymentOrder;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "orderObjcet")
    private Set<Booking> bookings = new HashSet<>();

    public OrderObjcet() {
    }

    public OrderObjcet(Long userID) {
        this.userID = userID;
    }

    public OrderObjcet(Long userID, Long paymentOrder) {
        this.userID = userID;
        this.paymentOrder = paymentOrder;
    }

    public OrderObjcet(Long userID, Long paymentOrder, Set<Booking> bookings) {
        this.userID = userID;
        this.paymentOrder = paymentOrder;
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

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "OrderObjcet{" +
                "orderID=" + orderID +
                ", userID=" + userID +
                ", paymentOrder=" + paymentOrder +
                ", bookings=" + bookings +
                '}';
    }
}
