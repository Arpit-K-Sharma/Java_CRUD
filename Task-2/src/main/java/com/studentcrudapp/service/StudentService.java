package com.studentcrudapp.service;

import com.studentcrudapp.model.Student;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Named
@ApplicationScoped
public class StudentService {

    @PersistenceContext
    private EntityManager em;


    public void save(Student student) {
        if (student.getId() == 0) {
            em.persist(student);
        } else {
            em.merge(student);
        }
    }

    public void delete(Student student) {
        Student toDelete = em.find(Student.class, student.getId());
        if (toDelete != null) {
            em.remove(toDelete);
        }
    }

    public List<Student> getAll() {
        return em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }

    public Student findById(int id) {
        return em.find(Student.class, id);
    }
}