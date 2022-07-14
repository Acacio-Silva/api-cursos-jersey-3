package com.project.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Connection {

    public static EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("crud");

    public EntityManager getConnection(){
        return emf.createEntityManager();
    }

}
