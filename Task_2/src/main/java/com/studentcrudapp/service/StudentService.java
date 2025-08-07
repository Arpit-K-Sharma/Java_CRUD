package com.studentcrudapp.service;

import com.studentcrudapp.model.Student;
import jakarta.persistence.*;
import java.util.List;
public class StudentService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentPU");

    public void save(Student student) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (student.getId() == 0) {
                em.persist(student);
            } else {
                em.merge(student);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Student student) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student toDelete = em.find(Student.class, student.getId());
            if (toDelete != null) {
                em.remove(toDelete);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Student> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Student findById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Student.class, id);
        } finally {
            em.close();
        }
    }
}