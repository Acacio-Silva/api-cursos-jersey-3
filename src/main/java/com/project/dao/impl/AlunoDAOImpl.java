package com.project.dao.impl;

import com.project.dao.AlunoDAO;
import com.project.models.aluno.Aluno;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import java.util.List;


public class AlunoDAOImpl implements AlunoDAO {

    private ModelMapper mapper = new ModelMapper();

    @Override
    public Aluno save(Aluno aluno) {

        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("crud");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(aluno);

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

        return aluno;
    }

    @Override
    public List<Aluno> findAll() {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("crud");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("SELECT a FROM Aluno a");
        List<Aluno> alunos = query.getResultList();
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
        return alunos;
    }

    @Override
    public Aluno findById(Integer id) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("crud");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Aluno aluno = entityManager.find(Aluno.class, id);

        entityManager.close();
        entityManagerFactory.close();
        return aluno;
    }

    @Override
    public Aluno findByCpf(String cpf) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("crud");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String jpql = "select a from Aluno a where a.cpf = " + "'" + cpf + "'";

        TypedQuery<Aluno> alunoTypedQuery = entityManager
                .createQuery(jpql, Aluno.class);

        Aluno aluno = alunoTypedQuery.getSingleResult();

        entityManager.close();
        entityManagerFactory.close();
        return aluno;
    }

    @Override
    public void remove(Integer id) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("crud");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Aluno aluno = entityManager.find(Aluno.class, id);
        System.out.println(aluno.toString());
        entityManager.getTransaction().begin();
        entityManager.remove(aluno);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();

    }

    @Override
    public Aluno update(Integer id, Aluno alunoRecebido) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("crud");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Aluno aluno = entityManager.find(Aluno.class, id);
        aluno.setId(id);
        aluno.setNome(alunoRecebido.getNome());
        aluno.setSexo(alunoRecebido.getSexo());
        aluno.setCpf(alunoRecebido.getCpf());
        aluno.setSituacao(alunoRecebido.getSituacao());
        aluno.setCurso(alunoRecebido.getCurso());
        System.out.println(alunoRecebido.getCurso());

        entityManager.getTransaction().begin();
        entityManager.merge(aluno);
        entityManager.getTransaction().commit();


        return aluno;
    }
}
