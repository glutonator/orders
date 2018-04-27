package com.orders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderObjcetRepository orderObjcetRepository;
    @Autowired
    private BookingRepository bookingRepository;

    //FU7
    // create new order/ CreateNewRelations / in orderobject specified eventid and ticketid
    public CreateNewRelationsRES createRelations (OrderObjcet orderObjcet) {
        for(Booking b : orderObjcet.getBookings()) {
            b.setOrderObjcet(orderObjcet);
            b.setRelationCreationDate(LocalDateTime.now());
            b.setRelationStatus(true);
        }
        orderObjcet.setPaymentOrder(orderObjcetRepository.count()+10);
        orderObjcetRepository.save(orderObjcet);
        //update tickets status
        //for UpdateTicketStatus();
        //response if saving is successful
        return new CreateNewRelationsRES(true,orderObjcet.getOrderID());
    }

    //FU7
    //update ticket status to occupied
    public boolean updateTicketStatus(Long eventId, Long ticketId, String TicketStatus) {
        final String uri = "http://localhooooost:8080/managment/{eventid}/{ticketid}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("eventid", eventId.toString());
        params.put("ticketid", ticketId.toString());
        params.put("ticketStatus", TicketStatus);

        Ticket ticketUpdate = new Ticket(eventId,ticketId,TicketStatus);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(uri,ticketUpdate,params);
        return true;
    }

    //FU8 - showUserTickets
    //show all orders of specific user
    public Iterable<OrderObjcet> findUserOrders (Long userid) {
        return orderObjcetRepository.findByUserID(userid);
    }

    //FU9
    //MakeResignation
    public MakeResignationRES makeResignation (Long orderid) {
        OrderObjcet orderObjcet=orderObjcetRepository.findById(orderid).orElse(null);
        for(  Booking b: orderObjcet.getBookings()) {
            //dla kazdego b trzeba wyslać UpdateTicketStatus
        }
        return new MakeResignationRES(true,orderid, orderObjcet.getPaymentOrder());
    }


    //FU11
    //show all orders of specific event
    public List<Booking> findAllTicketsFromEvent (Long eventid) {
        return bookingRepository.findByEventID(eventid);
    }

    //FU11
    //cancel event and all tickets
    public boolean cancelTicketsForEvent (Long eventid) {
        List<Booking> bookingList = findAllTicketsFromEvent(eventid);
        if (bookingList != null) {
            //trzeba zaupdateować statusy biletów z mikroserv zarz wydarzeniami
            //UpdateTicketStatusForEvent(eventid);
            return true;
        } else {
            //no records in databese
            return false;
        }
    }

    //FU11
    public boolean updateTicketStatusForEvent(Long eventid) {
        //tutaj trzeba zapytanie do mikroserwisu zarz wydarz by zminił stan biletow
        return true;
    }


    //test of consuming rest api
    public String tttest() {
        Logger log = LoggerFactory.getLogger(OrdersApplication.class);
        final String uri = "http://localhost:8080/orders/new_order2";
        OrderObjcet orderObjcet = new OrderObjcet((long)88,(long)45);
        RestTemplate restTemplate = new RestTemplate();
        CreateNewRelationsRES resp=restTemplate.postForObject(uri,orderObjcet,CreateNewRelationsRES.class);
        log.info(resp.toString());
        return resp.toString();
    }


    //its finally working
    //rollback by default on RuntimeException class, i think not tested
    @Transactional(rollbackFor = Exception.class)
    public String testaddNewOrder(Long iduser, Long idevent, Long idticket) throws Exception {

        OrderObjcet o = new OrderObjcet();
        o.setUserID(iduser);
        Booking b = new Booking();
        b.setEventID(idevent);
        b.setTicketID(idticket);
        b.setRelationCreationDate(LocalDateTime.now());
        b.setOrderObjcet(o);
        o.getBookings().add(b);
        orderObjcetRepository.save(o);
        if(iduser==888) {
            throw new Exception();
            //throw new InvalidUserItemException("qwertyuiop"+o.getOrderID()+o.getUserID());
            // return "Saved";
        }
        else {
            OrderObjcet oo = new OrderObjcet();
            oo.setUserID(iduser);
            Booking bb = new Booking();
            bb.setEventID(idevent);
            bb.setTicketID(idticket);
            bb.setRelationCreationDate(LocalDateTime.now());
            bb.setOrderObjcet(oo);
            oo.getBookings().add(bb);
            orderObjcetRepository.save(oo);
            return "Saved";
        }
    }

}
