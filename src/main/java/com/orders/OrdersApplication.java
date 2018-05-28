package com.orders;

import io.jsonwebtoken.impl.TextCodec;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static springfox.documentation.builders.PathSelectors.regex;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;


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

        System.out.print("Microservice starts");
//        // =======================================
//
//
//        //
//
//        // Create a Post
//        OrderObjcet post = new OrderObjcet((long) 22);
//        post.setPaymentOrder(orderObjcetRepository.count() + 10);
//        post.setStatus(true);
//        //post.setUserID((long)22);
//        // Create two tags
//        //LocalDateTime tempdate=LocalDateTime.of(2018,04,21,12,14,57);
//        Booking comment1 = new Booking(Long.valueOf(44), Long.valueOf(10), LocalDateTime.now(), true);
////        Booking comment1  = new Booking(Long.valueOf(22),Long.valueOf(33));
//        comment1.setOrderObjcet(post);
//        Booking comment2 = new Booking((long) 44, (long) 11, LocalDateTime.now(), true);
////        Booking comment2 = new Booking((long)45,(long)45);
//
//        comment2.setOrderObjcet(post);
//
//
//        // Add tag references in the post
//        post.getBookings().add(comment1);
//        post.getBookings().add(comment2);
//
//        // Add post reference in the tags
//        //tag1.getOrderObjcets().add(post);
//        //tag2.getOrderObjcets().add(post);
//
//        orderObjcetRepository.save(post);
//        // OrderObjcet orderObjcet2= orderObjcetRepository.findById((long)1).orElse(null);
//
//
//        OrderObjcet orderObjcet = orderObjcetRepository.findById((long) 1).orElse(null);
//        Booking comment3 = new Booking((long) 44, (long) 5, LocalDateTime.now(), true);
//        comment3.setOrderObjcet(orderObjcet);
//        orderObjcet.getBookings().add(comment3);
//        orderObjcetRepository.save(orderObjcet);
        // =======================================

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");



        //authorization
        final String authHeader = request.getHeader("authorization");
        String pathToRequest = request.getServletPath();

        //To Asia microsevice
        if (pathToRequest.startsWith("/orders/event/delete/") == true || pathToRequest.contains("swagger") || pathToRequest.startsWith("/v2")) {
            chain.doFilter(req, res);
        } else {
            if (authHeader == null) {
                pathToRequest = request.getServletPath();
                if (pathToRequest.startsWith("/orders/error")) {
                    chain.doFilter(req, res);
                } else {
                    response.sendRedirect("/orders/error/2");
                }
            } else {
                if (pathToRequest.startsWith("/orders/error")) {
                    chain.doFilter(req, res);
                } else {

                    final String token = authHeader.substring(7);
//                final String token2[] = token.split("\\.");
//                final String message = token2[1];
//        final String signature =token2[1];
//        String payload = TextCodec.BASE64URL.decodeToString(message);
                    Claims claims = null;

                    final String secretKey = "401b09eab3c013d4ca54922bb802bec8fd5318192b0a75f201d8b3727429090fb3" +
                            "37591abd3e44453b954555b7a0812e1081c39b740293f765eae731f5a65ed1";

                    try {
                        claims = Jwts.parser().setSigningKey(secretKey.getBytes("UTF-8")).parseClaimsJws(token).getBody();
                        request.setAttribute("expirationDate", claims.get("expirationDate"));
                        request.setAttribute("permissionId", claims.get("permissionId"));
                        request.setAttribute("userId", claims.get("userId"));


                        String string = (String) claims.get("expirationDate");
                        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZZ").withLocale(Locale.ROOT);
                        DateTime dt = formatter.parseDateTime(string);
                        DateTime localDateTime = DateTime.now(DateTimeZone.forID("Poland"));

                        // valid token expirationDate
                        pathToRequest = request.getServletPath();
                        if (pathToRequest.equals("/orders/error/1")) {

                            chain.doFilter(req, res);
                        } else {
                            if (localDateTime.isBefore(dt)) {
//                                System.out.print("iffffffffffffff");
                                chain.doFilter(req, res);

                            } else {
//                                System.out.print("Elsssseeeeeeee");
                                response.sendRedirect("/orders/error/1");
                            }
                        }
                    } catch (final io.jsonwebtoken.SignatureException e) {
                        throw new ServletException("Invalid token");
                    }
                    catch (Exception e) {
                        response.sendRedirect("/orders/error/2");
//                    chain.doFilter(req, res);
                    }
                }
            }

        }


    }

    @Override
    public void destroy() {

    }


}
