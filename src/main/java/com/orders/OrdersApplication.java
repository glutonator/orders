package com.orders;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

@SpringBootApplication
public class OrdersApplication implements CommandLineRunner {

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

    @Override
    public void run(String... args) throws Exception {

        // =======================================

        // Create a Post
		OrderObjcet post = new OrderObjcet((long)22);
		post.setPaymentOrder(orderObjcetRepository.count()+10);
        //post.setUserID((long)22);
		// Create two tags
        //LocalDateTime tempdate=LocalDateTime.of(2018,04,21,12,14,57);
        Booking comment1  = new Booking(Long.valueOf(22),Long.valueOf(33),LocalDateTime.now(),true);
//        Booking comment1  = new Booking(Long.valueOf(22),Long.valueOf(33));
		comment1.setOrderObjcet(post);
        Booking comment2 = new Booking((long)45,(long)45,LocalDateTime.now(),true);
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
        Booking comment3 = new Booking((long)999,(long)999,LocalDateTime.now(),true);
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
}
