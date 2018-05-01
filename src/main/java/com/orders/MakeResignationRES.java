package com.orders;

public class MakeResignationRES {
    private boolean status;
    private Long orderID;
    private Long paymentOrder;
    private Long userID;

    public MakeResignationRES() {
    }

    public MakeResignationRES(boolean status, Long orderID, Long paymentOrder) {
        this.status = status;
        this.orderID = orderID;
        this.paymentOrder = paymentOrder;
    }

    public MakeResignationRES(boolean status, Long orderID, Long paymentOrder, Long userID) {
        this.status = status;
        this.orderID = orderID;
        this.paymentOrder = paymentOrder;
        this.userID = userID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public Long getPaymentOrder() {
        return paymentOrder;
    }

    public void setPaymentOrder(Long paymentOrder) {
        this.paymentOrder = paymentOrder;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "MakeResignationRES{" +
                "status=" + status +
                ", orderID=" + orderID +
                ", paymentOrder=" + paymentOrder +
                ", userID=" + userID +
                '}';
    }
}
