package com.project.dao.impl;

import com.project.dao.ProfessorDAO;
import com.project.models.aluno.Aluno;
import com.project.models.professor.Professor;
import jakarta.persistence.*;

import java.util.List;

public class ProfessorDAOImpl implements ProfessorDAO {

    @Override
    public Professor save(Professor professor) {

        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("crud");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(professor);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
        return professor;
    }

    @Override
    public List<Professor> findAll() {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("crud");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createQuery("SELECT p FROM Professor p");
        List<Professor> professores = query.getResultList();

        entityManager.close();
        entityManagerFactory.close();
        return professores;
    }

    @Override
    public Professor findById(Integer id) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("curd");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Professor professor = entityManager.find(Professor.class, id);

        entityManager.close();
        entityManagerFactory.close();
        return professor;
    }

    @Override
    public Professor findByCpf(String cpf) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("crud");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String jpql = "select a from Professor a where a.cpf = " + "'" + cpf + "'";

        TypedQuery<Professor> professorTypedQuery = entityManager
                .createQuery(jpql, Professor.class);

        Professor professor = professorTypedQuery.getSingleResult();

        entityManager.close();
        entityManagerFactory.close();
        return professor;
    }

    @Override
    public void remove(Integer id) {

        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("crud");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Professor professor = entityManager.find(Professor.class, id);

        entityManager.remove(professor);

        entityManager.close();
        entityManagerFactory.close();

    }

    @Override
    public Professor update(Integer id, Professor professor) {

        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("crud");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Professor buscaProfessor = entityManager.find(Professor.class, id);
        buscaProfessor.setId(id);
        buscaProfessor.setNome(professor.getNome());
        buscaProfessor.setCpf(professor.getCpf());
        buscaProfessor.setSalario(professor.getSalario());
        buscaProfessor.setSexo(professor.getSexo());
        buscaProfessor.setTipoContrato(professor.getTipoContrato());
        buscaProfessor.setCurso(professor.getCurso());

        entityManager.getTransaction().begin();
        entityManager.merge(buscaProfessor);
        entityManager.getTransaction().commit();


        return buscaProfessor;
    }
}
