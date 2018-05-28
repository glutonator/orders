package com.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller    // This means that this class is a Controller
//,produces = "text/plain;charset=UTF-8"
//consumes = MediaType.TEXT_PLAIN_VALUE
//APPLICATION_FORM_URLENCODED_VALUE
@RequestMapping(path = "/orders/") // This means URL's start with /demo (after Application path)
public class MainController {
    @Autowired
    OrderService orderService;

    @Autowired
    private OrderObjcetRepository orderObjcetRepository;
    @Autowired
    private BookingRepository bookingRepository;



//////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////
    //FU7
    // create new order/ CreateNewRelations / in orderobject specified eventid and ticketid
    //,produces = MediaType.TEXT_PLAIN_VALUE
    @RequestMapping(method = RequestMethod.POST, value = "/new_order",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JsonErrorResponses createNewRelations(OrderObjcet orderObjcet,HttpServletRequest httpServletRequest) {
        //RequestBody
        String userIdTokenStr = httpServletRequest.getAttribute("userId").toString();
        Long userIdToken =Long.parseLong(userIdTokenStr);
        return orderService.createRelations(orderObjcet,userIdToken);
    }

    //FU8
    //show all orders of specific user
    @RequestMapping(method = RequestMethod.GET, value = "/user/{userid}")
    public @ResponseBody
    JsonErrorResponses showUserTickets(@PathVariable("userid") Long userid,HttpServletRequest httpServletRequest) {
        String userIdTokenStr = httpServletRequest.getAttribute("userId").toString();
        Long userIdToken =Long.parseLong(userIdTokenStr);
        return orderService.findUserOrders(userid,userIdToken);
    }

    //FU9
    //MakeResignation
    @RequestMapping(method = RequestMethod.POST, value = "/resignation/{orderid}",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JsonErrorResponses makeResignation(@PathVariable("orderid") Long orderid,HttpServletRequest httpServletRequest) {
        String userIdTokenStr = httpServletRequest.getAttribute("userId").toString();
        Long userIdToken =Long.parseLong(userIdTokenStr);
        return orderService.makeResignation(orderid,userIdToken);
    }

    //FU11
    //show all orders of specific event
    @RequestMapping(method = RequestMethod.GET, value = "/event/{eventid}")
    public @ResponseBody
    JsonErrorResponses showAllTicketsFromEvent(@PathVariable("eventid") Long eventid,HttpServletRequest httpServletRequest) {
        String permissionIdTokenStr = httpServletRequest.getAttribute("permissionId").toString();
//        boolean permissionIdToken =Boolean.parseBoolean(permissionIdTokenStr);
        boolean permissionIdToken="1".equals(permissionIdTokenStr);

        if(permissionIdToken==true) {
            return orderService.findAllTicketsFromEvent(eventid);
        }
        else {
            return new JsonErrorResponses(200, "", false, new Error(207, "fail", "Permission denied, you are not admin"));

        }
    }

    //FU11
    //cancel event and all tickets
    @RequestMapping(method = RequestMethod.GET, value = "/event/delete/{eventid}")
    public @ResponseBody
    StringRES cancelEvent(@PathVariable("eventid") Long eventid) {
        return orderService.cancelTicketsForEvent(eventid);
    }

    //token not valid
    @RequestMapping(method = RequestMethod.GET, value = "/error/{errornr}")
    public @ResponseBody
    JsonErrorResponses tokenValidationFail(@PathVariable("errornr") int errornr) {
        return orderService.tokenValidationFail(errornr);
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////
// old api/tests

    // get order with specific idorder
    //@GetMapping(path = "/{order}") // Map ONLY GET Requests
    @RequestMapping(method = RequestMethod.GET, value = "/{order}")
    public @ResponseBody
    JsonErrorResponses getOrderDetails(@PathVariable("order") Long idorder,HttpServletRequest httpServletRequest) {
        String userIdTokenStr = httpServletRequest.getAttribute("userId").toString();
        Long userIdToken =Long.parseLong(userIdTokenStr);

        return orderService.getOrderDetails(idorder,userIdToken);

//        return orderObjcetRepository.findById(idorder).orElse(null);
    }


    // get all orders
    @RequestMapping(method = RequestMethod.GET, value = "/all_orders")
    public @ResponseBody
    JsonErrorResponses getAllOrders(HttpServletRequest httpServletRequest) {
//        String dsdsds = (Date)httpServletRequest.getAttribute("expirationDate");

        String permissionIdTokenStr = httpServletRequest.getAttribute("permissionId").toString();
        //boolean permissionIdToken =Boolean.parseBoolean(permissionIdTokenStr);
        boolean permissionIdToken="1".equals(permissionIdTokenStr);
        if(permissionIdToken==true) {
//            return orderService.findAllTicketsFromEvent(eventid);
            return new JsonErrorResponses(200, orderObjcetRepository.findAll(), true, new Error(200, "success", "everything is fine, action finished properly"));
        }
        else {
           return new JsonErrorResponses(200, "", false, new Error(207, "fail", "Permission denied, you are not admin"));

        }

//        return orderObjcetRepository.findAll();
    }

}