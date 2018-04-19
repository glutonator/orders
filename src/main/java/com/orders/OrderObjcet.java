package com.orders;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
//import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity // This tells Hibernate to make a table out of this class
public class OrderObjcet {
    @Id
    @SequenceGenerator(name = "SEQ_GEN_ORDER", sequenceName = "SEQ_GEN_ORDER", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN_ORDER")
    private Long orderID;

    private Long userID;

    private float paymentOrder;
////fetch = FetchType.LAZY
//    @ManyToMany(fetch = FetchType.EAGER,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            })
//    @JoinTable(name = "TicketList",
//            joinColumns = {@JoinColumn(name = "IdOrder")},
//            inverseJoinColumns = {@JoinColumn(name = "IdBooked")})
//    private Set<Booking> bookings = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "orderObjcet")
    private Set<Booking> bookings = new HashSet<>();
//    private Integer IdUser;
//
//    private Integer IdEvent;
//
//    private Integer IdTicket;

    //private Integer paid;

    // @Column(name = "orderDateTimeStamp", columnDefinition="DATETIME")
    // @Temporal(TemporalType.TIMESTAMP)

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//    private LocalDateTime order_date_time_stamp;

    public OrderObjcet() {
    }

    public OrderObjcet(Long userID) {
        this.userID = userID;
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
        userID = userID;
    }

    public float getPaymentOrder() {
        return paymentOrder;
    }

    public void setPaymentOrder(float paymentOrder) {
        paymentOrder = paymentOrder;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

//    public LocalDateTime getOrder_date_time_stamp() {
//        return order_date_time_stamp;
//    }
//
//    public void setOrder_date_time_stamp(LocalDateTime order_date_time_stamp) {
//        this.order_date_time_stamp = order_date_time_stamp;
//    }

    @Override
    public String toString() {
        return "OrderObjcet{" +
                "OrderID=" + orderID +
                ", UserID=" + userID +
                ", PaymentOrder=" + paymentOrder +
                ", bookings=" + bookings +
                //", order_date_time_stamp=" + order_date_time_stamp +
                '}';
    }
}
