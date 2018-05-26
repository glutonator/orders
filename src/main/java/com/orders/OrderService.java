package com.orders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderObjcetRepository orderObjcetRepository;
    @Autowired
    private BookingRepository bookingRepository;

    //FU7
    // create new order/ CreateNewRelations / in orderobject specified eventid and ticketid
    public JsonErrorResponses createRelations(OrderObjcet orderObjcet, Long userIdToken) {
        if (orderObjcet.getUserID().equals(userIdToken) == true) {
            //////////////////////////////////
            for (Booking b : orderObjcet.getBookings()) {
                b.setOrderObjcet(orderObjcet);
                b.setRelationCreationDate(LocalDateTime.now());
                b.setRelationStatus(true);
            }
            orderObjcet.setPaymentOrder(orderObjcetRepository.count() + 10);
            orderObjcet.setStatus(true);
//            orderObjcetRepository.save(orderObjcet);
            for (Booking b : orderObjcet.getBookings()) {
                int statusTemp = updateTicketStatus(b.getTicketID(), "OCCUPIED");
//                if(statusTemp==0) {
//                    return new JsonErrorResponses(200,orderObjcet,true,new Error(200,"success","everything is fine, action finished properly"));
//                }
                //bad connection
                if (statusTemp == 1) {
                    return new JsonErrorResponses(200, null, false, new Error(204, "fail ", "connection to Tickets Microservice refused"));

                }

                //tickets not found
                //statusTemp==2
                else if (statusTemp == 2) {
                    return new JsonErrorResponses(200, null, false, new Error(205, "fail", "Tickets not found"));
                }
            }
            orderObjcetRepository.save(orderObjcet);

            return new JsonErrorResponses(200, orderObjcet, true, new Error(200, "success", "everything is fine, action finished properly"));


            ////////////
            //response if saving is successful
//            return new CreateNewRelationsRES(true,orderObjcet.getOrderID());
//            ObjectMapper mapper = new ObjectMapper();
//            String jsonInString="";
//            try {
////                jsonInString = mapper.writeValueAsString(orderObjcet);
//                jsonInString=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderObjcet);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//            JsonErrorResponses jsonErrorResponses = new JsonErrorResponses(200,orderObjcet,true,new Error(201,"dsads","sdsd"));

//            return new JsonErrorResponses(200,orderObjcet,true,new Error(200,"success","everything is fine, action finished properly"));
//            return jsonErrorResponses;
        } else {
            return new JsonErrorResponses(200, null, false, new Error(203, "fail", "Token belong to different user"));

//            return new CreateNewRelationsRES(false,orderObjcet.getOrderID());
        }
//        return new JsonErrorResponses(200,orderObjcet,true,new Error(204,"fail ","connection to Tickets Microservice refused"));
    }

    //FU7
    //update ticket status to occupied
    //0-success 1-bad connection 2-ticket not found
    public int updateTicketStatus(Long ticketId, String TicketStatus) {

        Logger log = LoggerFactory.getLogger(OrdersApplication.class);
//        final String uri = "http://localhost:8080/tickets/updateTicketStatus";
////        final String uri = "http://localhost:8080/tickets/updateTicketStatus?id=3&status=fdfd";
//        final String uri2 = uri + "?id="+ticketId+"&status="+TicketStatus;
        final String uri = "http://et-microservice.westeurope.cloudapp.azure.com:8181/tickets/status/";
//        final String uri = "http://localhost:8080/tickets/";
        final String uri2 = uri + "?ticketid=" + ticketId + "&status=" + TicketStatus;

        RestTemplate restTemplate = new RestTemplate();
        StringRES resp = null;
        try {
            resp = restTemplate.postForObject(uri2, null, StringRES.class);
        } catch (RestClientException e) {
            return 1;
        }
        log.info(resp.toString());
//        return resp.toString();
        if (resp.getStatus() == true) {
            return 0;
        }
        //ticket not found
        else {
            return 2;
        }


//        final String uri = "http://localhost:8080/tickets/updateTicketStatus/{eventid}/{ticketid}";
//        Map<String, String> params = new HashMap<String, String>();
//        //params.put("eventid", eventId.toString());
//        params.put("id", ticketId.toString());
//        params.put("status", TicketStatus);
//        Ticket ticketUpdate = new Ticket(ticketId,TicketStatus);
//
////        Ticket ticketUpdate = new Ticket(eventId,ticketId,TicketStatus);
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.put(uri,ticketUpdate,params);
//        return true;
    }

    //FU8 - showUserTickets
    //show all orders of specific user
    public JsonErrorResponses findUserOrders(Long userid, Long userIdToken) {
        Iterable<OrderObjcet> orderObjcets = null;
        if (userid.equals(userIdToken) == true) {
            orderObjcets = orderObjcetRepository.findByUserID(userid);
            //if user dont have orders return empty list
            return new JsonErrorResponses(200, orderObjcets, true, new Error(200, "success", "everything is fine, action finished properly"));

        } else {
            return new JsonErrorResponses(200, null, false, new Error(203, "fail", "Token belong to different user"));

        }

//        return orderObjcetRepository.findByUserID(userid);
    }

    //FU9
    //MakeResignation
    //ToDo create errors to rollback in case failure
    @Transactional
    public JsonErrorResponses makeResignation(Long orderid, Long userIdToken) {
        OrderObjcet orderObjcet = orderObjcetRepository.findById(orderid).orElse(null);
        if (orderObjcet == null) {
            return new JsonErrorResponses(200, null, false, new Error(206, "fail ", "Order not found"));
        } else {

            //user id not the same
            if (orderObjcet.getUserID().equals(userIdToken) == false) {
                return new JsonErrorResponses(200, null, false, new Error(203, "fail", "Token belong to different user"));

            } else {

                for (Booking b : orderObjcet.getBookings()) {
                    int statusTemp = updateTicketStatus(b.getTicketID(), "CANCELED");
                    statusTemp=0;
                    if (statusTemp == 1) {
                        return new JsonErrorResponses(200, null, false, new Error(204, "fail ", "connection to Tickets Microservice refused"));

                    }

                    //tickets not found
                    //statusTemp==2
                    else if (statusTemp == 2) {
                        return new JsonErrorResponses(200, null, false, new Error(205, "fail", "Tickets not found"));
                    }


                    b.setRelationModificationDate(LocalDateTime.now());
                    b.setRelationStatus(false);
                    bookingRepository.save(b);
                    //dla kazdego b trzeba wyslać UpdateTicketStatus
                }
                orderObjcet.setStatus(false);
                orderObjcetRepository.save(orderObjcet);

                return new JsonErrorResponses(200, orderObjcet, true, new Error(200, "success", "everything is fine, action finished properly"));
            }
        }

        //todo: to było wczesniej:
//        if (returnPayment(orderObjcet.getPaymentOrder()) == true) {
//            return new MakeResignationRES(true, orderid, orderObjcet.getPaymentOrder(), orderObjcet.getUserID());
//        } else {
//            // payment fained, needed rollback
//            return new MakeResignationRES(false, orderid, orderObjcet.getPaymentOrder(), orderObjcet.getUserID());
//
//        }
    }


    //FU11
    //show all orders of specific event
    public JsonErrorResponses findAllTicketsFromEvent(Long eventid) {
        List<Booking>  bookingList= bookingRepository.findByEventID(eventid);
        return new JsonErrorResponses(200, bookingList, true, new Error(200, "success", "everything is fine, action finished properly"));

    }

    //FU11
    //cancel event and all tickets
    @Transactional
    public StringRES cancelTicketsForEvent(Long eventid) {
        List<Booking> bookingList = (List<Booking>) findAllTicketsFromEvent(eventid).getResult();
        if (bookingList != null) {
            //teraz trzeba zamienić statusy zamowien na niewazne i wyslac kase
            LinkedList<Long> tmpPaymentOrderList = new LinkedList<>();
            for (Booking b : bookingList) {
                if (b.getOrderObjcet().isStatus() == true) {
                    tmpPaymentOrderList.add(b.getOrderObjcet().getPaymentOrder());
                    b.getOrderObjcet().setStatus(false);
                    orderObjcetRepository.save(b.getOrderObjcet());
                } else {
                    //nothing
                }
                b.setRelationStatus(false);
                bookingRepository.save(b);
            }
            for (Long p : tmpPaymentOrderList) {
                if (returnPayment(p) == true) {
                    //nothing
                }
//                else{
//                    try {
//                        throw new CancelTicketsForEventExeption();
//                    }
//                    catch (CancelTicketsForEventExeption e) {
//                        return new StringRES(false);
//                    }
//                }
            }

        } else {
            //no records in database
        }
        return new StringRES(true);
    }

    public boolean returnPayment(Long paymentOrder) {
        //connecting to external payment service
        return true;
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
        OrderObjcet orderObjcet = new OrderObjcet((long) 88, (long) 45);
        RestTemplate restTemplate = new RestTemplate();
        CreateNewRelationsRES resp = restTemplate.postForObject(uri, orderObjcet, CreateNewRelationsRES.class);
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
        if (iduser == 888) {
            throw new Exception();
            //throw new InvalidUserItemException("qwertyuiop"+o.getOrderID()+o.getUserID());
            // return "Saved";
        } else {
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

    //return json with fail message
    public StringRES tokenValidationFail() {
        return new StringRES(false);
    }

}
