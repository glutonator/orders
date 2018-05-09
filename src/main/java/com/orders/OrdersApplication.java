package com.orders;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.servlet.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@SpringBootApplication
public class OrdersApplication implements CommandLineRunner, Filter {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private OrderObjcetRepository orderObjcetRepository;

	@PersistenceContext
	private EntityManager em;

//    private EntityManagerFactory emf;

    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class, args);
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.orders"))
                .paths(regex("/orders.*"))
                .build()
                .apiInfo(apiInfo());
        //    private ApiInfo apiInfo() {
//        return new ApiInfo(
//                "My REST API",
//                "Some custom description of API.",
//                "API TOS",
//                "Terms of service",
//                new Contact("John Doe", "www.example.com", "myeaddress@company.com"),
//                "License of API", "API license URL", Collections.emptyList());
//    }
    }

private ApiInfo apiInfo() {
    ApiInfo apiInfo = new ApiInfo(
            "Mikroserwis zarządzania udziałem w wydarzeniach - REST API",
            ".",
            "API 4",
            "",
            "Filip Lewczak",
            "API License URL", null
    );
    return apiInfo;
}

    @Override
    public void run(String... args) throws Exception {

        // =======================================

        // Create a Post
		OrderObjcet post = new OrderObjcet((long)22);
		post.setPaymentOrder(orderObjcetRepository.count()+10);
		post.setStatus(true);
        //post.setUserID((long)22);
		// Create two tags
        //LocalDateTime tempdate=LocalDateTime.of(2018,04,21,12,14,57);
        Booking comment1  = new Booking(Long.valueOf(44),Long.valueOf(10),LocalDateTime.now(),true);
//        Booking comment1  = new Booking(Long.valueOf(22),Long.valueOf(33));
		comment1.setOrderObjcet(post);
        Booking comment2 = new Booking((long)44,(long)11,LocalDateTime.now(),true);
//        Booking comment2 = new Booking((long)45,(long)45);

        comment2.setOrderObjcet(post);


		// Add tag references in the post
		post.getBookings().add(comment1);
		post.getBookings().add(comment2);

		// Add post reference in the tags
		//tag1.getOrderObjcets().add(post);
		//tag2.getOrderObjcets().add(post);

		orderObjcetRepository.save(post);
       // OrderObjcet orderObjcet2= orderObjcetRepository.findById((long)1).orElse(null);


        OrderObjcet orderObjcet= orderObjcetRepository.findById((long)1).orElse(null);
        Booking comment3 = new Booking((long)44,(long)5,LocalDateTime.now(),true);
        comment3.setOrderObjcet(orderObjcet);
        orderObjcet.getBookings().add(comment3);
        orderObjcetRepository.save(orderObjcet);
        //EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        OrderObjcet nowy = em.find(OrderObjcet.class, 2L);
//        nowy.getBookings().size();
//        Booking tag3 = new Booking((long) 99, (long) 99, (long) 99);
//
//        nowy.getBookings().add(tag3);
//        tag3.getOrderObjcets().add(nowy);
//        em.getTransaction().commit();
//        em.close();

//		OrderObjcet nowy = orderObjcetRepository.findById((long)3).orElse(null);
//		Booking tag3 = new Booking((long)99,(long)99,(long)99);
//		System.out.println(Hibernate.isInitialized(nowy.getBookings()));
////		OrderObjcet ttt=this.em.find(OrderObjcet.class,(long)2);
////		ttt.getBookings().size();
////		ttt.getBookings().add(tag3);
////		tag3.getOrderObjcets().add(ttt);
////		orderObjcetRepository.save(ttt);
//
//		nowy.getBookings().size();
//		nowy.getBookings().add(tag3);
//		tag3.getOrderObjcets().add(nowy);
//		orderObjcetRepository.save(nowy);
//        System.out.println(Hibernate.isInitialized(nowy.getBookings()));


        // =======================================

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response =  (HttpServletResponse) res;

//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Origin", "*");

//        response.setHeader("Acess-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, Authorization");


        chain.doFilter(req, res);

    }

    @Override
    public void destroy() {

    }
}
