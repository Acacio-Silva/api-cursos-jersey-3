package com.project.dao.impl;

import com.project.dao.ProfessorDAO;
import com.project.models.professor.Professor;
import jakarta.persistence.*;

import java.util.List;

public class ProfessorDAOImpl implements ProfessorDAO {

    @Override
    public Professor save(Professor professor) {

        System.out.println(professor.getTipoContrato());

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

        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("SELECT p FROM Professor p");
        List<Professor> professores = query.getResultList();
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
        return professores;
    }

    @Override
    public Professor findById(Integer id) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("crud");
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
        entityManager.getTransaction().begin();
        entityManager.remove(professor);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

    }

    @Override
    public Professor update(Integer id, Professor professorRecebido) {

        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("crud");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Professor professor = entityManager.find(Professor.class, id);

        Professor professorVerificado = verificaProfessorRecebido(professorRecebido, professor);

        entityManager.getTransaction().begin();
        professorVerificado.setId(id);
        entityManager.merge(professorVerificado);
        entityManager.getTransaction().commit();


        return professorVerificado;
    }

    private Professor verificaProfessorRecebido(Professor professorRecebido, Professor professor){
        if (professorRecebido.getNome() == null){
            professorRecebido.setNome(professor.getNome());
        }
        if (professorRecebido.getCpf() == null){
            professorRecebido.setCpf(professor.getCpf());
        }
        if (professorRecebido.getCurso() == null){
            professorRecebido.setCurso(professor.getCurso());
        }
        if (professorRecebido.getSalario() == null){
            professorRecebido.setSalario(professor.getSalario());
        }
        if (professorRecebido.getSexo() == null){
            professorRecebido.setSexo(professor.getSexo());
        }
        if (professorRecebido.getTipoContrato() == null){
            professorRecebido.setTipoContrato(professor.getTipoContrato());
        }
        return professorRecebido;
    }

}
