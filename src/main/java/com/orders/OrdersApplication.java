package com.orders;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
//		OrderObjcet post = new OrderObjcet();
//
//		// Create two tags
//		Booking tag1 = new Booking( Long.valueOf(11),Long.valueOf(22),Long.valueOf(33));
//		Booking tag2 = new Booking((long)45,(long)45,(long)45);
//
//
//		// Add tag references in the post
//		post.getBookings().add(tag1);
//		post.getBookings().add(tag2);
//
//		// Add post reference in the tags
//		tag1.getOrderObjcets().add(post);
//		tag2.getOrderObjcets().add(post);
//
//		orderObjcetRepository.save(post);
        //EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        OrderObjcet nowy = em.find(OrderObjcet.class, 2L);
        nowy.getBookings().size();
        Booking tag3 = new Booking((long) 99, (long) 99, (long) 99);

        nowy.getBookings().add(tag3);
        tag3.getOrderObjcets().add(nowy);
        em.getTransaction().commit();
        em.close();

//		OrderObjcet nowy = orderObjcetRepository.findById((long)2).orElse(null);
//		Booking tag3 = new Booking((long)99,(long)99,(long)99);
//		System.out.println(Hibernate.isInitialized(nowy.getBookings()));
//		OrderObjcet ttt=this.em.find(OrderObjcet.class,(long)2);
//		ttt.getBookings().size();
//		ttt.getBookings().add(tag3);
//		tag3.getOrderObjcets().add(ttt);
//		orderObjcetRepository.save(ttt);

//		nowy.getBookings().size();
//		nowy.getBookings().add(tag3);
//		tag3.getOrderObjcets().add(nowy);
//		orderObjcetRepository.save(nowy);


        // =======================================

    }
}
