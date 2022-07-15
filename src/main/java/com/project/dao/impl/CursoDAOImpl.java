package com.project.dao.impl;

import com.project.dao.CursoDAO;
import com.project.models.curso.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class CursoDAOImpl implements CursoDAO {

    @Override
    public Curso save(Curso curso) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("crud");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(curso);
        entityManager.getTransaction().commit();
        return curso;
    }

    @Override
    public Curso findById(Integer id) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("crud");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Curso curso = entityManager.find(Curso.class, id);

        entityManager.close();
        entityManagerFactory.close();

        return curso;
    }

    @Override
    public List<Curso> findAll() {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("crud");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("SELECT c FROM Curso c");
        List<Curso> cursos = query.getResultList();
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
        return cursos;
    }

    @Override
    public Curso update(Integer id, Curso cursoRecebido) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("crud");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Curso curso = entityManager.find(Curso.class, id);

        Curso cursoVerificado = verrificaCursoRecebido(cursoRecebido, curso);
        cursoVerificado.setId(id);
        entityManager.getTransaction().begin();
        entityManager.merge(cursoVerificado);
        entityManager.getTransaction().commit();

        return cursoVerificado;
    }

    @Override
    public void remove(Integer id) {

        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("crud");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Curso curso = entityManager.find(Curso.class, id);

        entityManager.getTransaction().begin();
        entityManager.remove(curso);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

    }

    private Curso verrificaCursoRecebido(Curso cursoRecebido, Curso curso){
        if (cursoRecebido.getNome() == null){
            cursoRecebido.setNome(curso.getNome());
        }
        if (cursoRecebido.getProfessor() == null){
            cursoRecebido.setProfessor(curso.getProfessor());
        }
        if (cursoRecebido.getQuantidadeAlunos() == null){
            cursoRecebido.setQuantidadeAlunos(curso.getQuantidadeAlunos());
        }
        if (cursoRecebido.getPreco() == null){
            cursoRecebido.setPreco(curso.getPreco());
        }
        if (cursoRecebido.getAlunos() == null){
            cursoRecebido.setAlunos(curso.getAlunos());
        }
        return cursoRecebido;
    }

}
