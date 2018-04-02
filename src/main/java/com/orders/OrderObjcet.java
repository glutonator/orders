package com.orders;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
//import java.sql.Date;
import java.time.LocalDateTime;


@Entity // This tells Hibernate to make a table out of this class
public class OrderObjcet {
    @Id
    @SequenceGenerator(name="SEQ_GEN_ORDER", sequenceName="SEQ_GEN_ORDER", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_GEN_ORDER")
    private Integer IdOrder;

//    private Integer IdUser;
//
//    private Integer IdEvent;
//
//    private Integer IdTicket;

    //private Integer paid;

   // @Column(name = "orderDateTimeStamp", columnDefinition="DATETIME")
   // @Temporal(TemporalType.TIMESTAMP)

   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
   private LocalDateTime order_date_time_stamp;


    public Integer getIdOrder() {
        return IdOrder;
    }

    public void setIdOrder(Integer idOrder) {
        IdOrder = idOrder;
    }

    public LocalDateTime getOrder_date_time_stamp() {
        return order_date_time_stamp;
    }

    public void setOrder_date_time_stamp(LocalDateTime order_date_time_stamp) {
        this.order_date_time_stamp = order_date_time_stamp;
    }
}
