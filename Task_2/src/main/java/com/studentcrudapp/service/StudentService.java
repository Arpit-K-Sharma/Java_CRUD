package com.studentcrudapp.service;

import com.studentcrudapp.model.Student;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Named
@ApplicationScoped
public class StudentService {

    @PersistenceContext
    private EntityManager em;


    @Transactional
    public void save(Student student) {
        if (student.getId() == 0) {
            em.persist(student);
        } else {
            em.merge(student);
        }
    }

    @Transactional
    public void delete(Student student) {
        Student toDelete = em.find(Student.class, student.getId());
        if (toDelete != null) {
            em.remove(toDelete);
        }
    }

    @Transactional
    public List<Student> getAll() {
        return em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }

    @Transactional
    public Student findById(int id) {
        return em.find(Student.class, id);
    }
}