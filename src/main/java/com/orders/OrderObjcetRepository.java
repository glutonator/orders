package com.orders;

import org.springframework.data.repository.CrudRepository;

import com.orders.OrderObjcet;

import java.util.List;
//public interface OrderObjcetRepository extends CrudRepository<OrderObjcet, Long> {

public interface OrderObjcetRepository extends CrudRepository<OrderObjcet, Long> {
    List<OrderObjcet> findByUserID(Long Userid);
}
