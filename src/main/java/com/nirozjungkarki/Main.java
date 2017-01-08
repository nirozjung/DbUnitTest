package com.nirozjungkarki;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
	
	public static void main(String[] args) {
        // Initializes the Entity manager

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pokebattlePU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Pokemon niroz = new Pokemon("Niroz");
        niroz.setType1(Type.ELECTRIC);
        em.persist(niroz);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }

}
