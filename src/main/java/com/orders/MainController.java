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
        boolean permissionIdToken =Boolean.parseBoolean(permissionIdTokenStr);
        if(permissionIdToken==true) {
            return orderService.findAllTicketsFromEvent(eventid);
        }
        else {
            return new JsonErrorResponses(200, null, false, new Error(207, "fail", "Permission denied, you are not admin"));

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
    @RequestMapping(method = RequestMethod.GET, value = "/error")
    public @ResponseBody
    StringRES tokenValidationFail() {
        return orderService.tokenValidationFail();
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////
// old api/tests

    //    @Autowired // This means to get the bean called userRepository
//    // Which is auto-generated by Spring, we will use it to handle the data
//    private UserRepository userRepository;
//
//    @GetMapping(path="/add") // Map ONLY GET Requests
//    public @ResponseBody String addNewUser (@RequestParam String name
//            , @RequestParam String email) {
//        // @ResponseBody means the returned String is the response, not a view name
//        // @RequestParam means it is a parameter from the GET or POST request
//
//        User n = new User();
//        n.setName(name);
//        n.setEmail(email);
//        userRepository.save(n);
//        return "Saved";
//    }
//

    // get order with specific idorder
    //@GetMapping(path = "/{order}") // Map ONLY GET Requests
    @RequestMapping(method = RequestMethod.GET, value = "/{order}")
    public @ResponseBody
    OrderObjcet getOrderDetails(@PathVariable("order") Long idorder) {
        //orderService.updateTicketStatus((long)45,"ppppp");
        //orderService.tttest();
        return orderObjcetRepository.findById(idorder).orElse(null);
    }

    // create order with specific iduser,idevent,idticket
    @RequestMapping(method = RequestMethod.POST, value = "/new_order_old",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String addNewOrder(@RequestParam Long iduser, @RequestParam Long idevent, @RequestParam Long idticket) {
        try {
            orderService.testaddNewOrder(iduser,idevent,idticket);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        OrderObjcet o = new OrderObjcet();
//        Booking b = new Booking();
//        b.setEventID(idevent);
//        b.setTicketID(idticket);
//        b.setOrderObjcet(o);
//        o.getBookings().add(b);
//        orderObjcetRepository.save(o);
        return "Saved";
    }


    //    @RequestMapping(method = RequestMethod.GET,value ="/test")
//    public @ResponseBody
//    String testtest() {
//        Logger log = LoggerFactory.getLogger(OrdersApplication.class);
//        RestTemplate restTemplate = new RestTemplate();
//        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
//        log.info(quote.toString());
//        return quote.toString();
//    }

//    // delete order with specific idorder
//    @RequestMapping(method = RequestMethod.DELETE,path ="/delete_order/{order}")
//    public @ResponseBody
//    void deleteNewOrder(@PathVariable("order") Integer idorder) {
//        orderObjcetRepository.deleteById(idorder);
//    }
//

    // get all orders
    @RequestMapping(method = RequestMethod.GET, value = "/all_orders")
    public @ResponseBody
    Iterable<OrderObjcet> getAllOrders(HttpServletRequest httpServletRequest) {
//        Date dsdsds = (Date)httpServletRequest.getAttribute("expirationDate");

        return orderObjcetRepository.findAll();
    }

}