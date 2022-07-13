package com.project.dao.impl;

import com.project.dao.CursoDAO;
import com.project.models.aluno.Aluno;
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

        Query query = entityManager.createQuery("SELECT c FROM Curso c");
        List<Curso> cursos = query.getResultList();

        entityManager.close();
        entityManagerFactory.close();
        return cursos;
    }

    @Override
    public Curso update(Integer id, Curso curso) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("crud");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Curso buscaCurso = entityManager.find(Curso.class, id);
        buscaCurso.setId(id);
        buscaCurso.setNome(curso.getNome());
        buscaCurso.setQuantidadeAlunos(curso.getQuantidadeAlunos());
        buscaCurso.setPreço(curso.getPreço());
        buscaCurso.setAlunos(curso.getAlunos());
        buscaCurso.setProfessor(curso.getProfessor());

        entityManager.getTransaction().begin();
        entityManager.merge(buscaCurso);
        entityManager.getTransaction().commit();

        return buscaCurso;
    }

    @Override
    public void remove(Integer id) {

        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("crud");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Curso curso = entityManager.find(Curso.class, id);

        entityManager.remove(curso);

        entityManager.close();
        entityManagerFactory.close();

    }
}
