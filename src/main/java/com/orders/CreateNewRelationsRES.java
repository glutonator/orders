package com.orders;

public class CreateNewRelationsRES {
    private boolean status;
    private Long orderID;

    public CreateNewRelationsRES(boolean status) {
        this.status = status;
    }

    public CreateNewRelationsRES(boolean status, Long orderid) {
        this.status = status;
        this.orderID = orderid;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getOrderid() {
        return orderID;
    }

    public void setOrderid(Long orderid) {
        this.orderID = orderid;
    }

    @Override
    public String toString() {
        return "CreateNewRelationsRES{" +
                "status=" + status +
                ", orderid=" + orderID +
                '}';
    }
}
