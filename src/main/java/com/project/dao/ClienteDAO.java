package com.project.dao;

import com.project.models.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteDAO{

    public static List<Cliente> clientes = new ArrayList<>();

    public void salvarCliente(Cliente cliente){

        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("crud");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

    }
    public List<Cliente> findAll(){
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("crud");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

       Query query = entityManager.createQuery("SELECT e FROM Cliente e");
       List<Cliente> clientes = query.getResultList();

        entityManager.close();
        entityManagerFactory.close();
       return clientes;
    }


    public Cliente findById(Long id){
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("crud");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Cliente cliente = entityManager.find(Cliente.class, id);

        entityManager.close();
        entityManagerFactory.close();
        return cliente;
    }

}
