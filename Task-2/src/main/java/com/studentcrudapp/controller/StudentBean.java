package com.studentcrudapp.controller;

import com.studentcrudapp.model.Student;
import com.studentcrudapp.service.StudentService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.*;

@ManagedBean
@ViewScoped
public class StudentBean implements Serializable {

    public StudentBean() {}

    private Student student = new Student();
    private List<Student> studentList = new ArrayList<>();
    private Map<String, List<String>> classSubjectMap = new HashMap<>();
    private List<String> classList = new ArrayList<>();
    private List<String> subjectList = new ArrayList<>();

    @ManagedProperty(value="#{studentService}")
    private StudentService service;

    @PostConstruct
    public void init() {
        classList = Arrays.asList("Class 1", "Class 2", "Class 3");

        classSubjectMap.put("Class 1", Arrays.asList("Math", "English"));
        classSubjectMap.put("Class 2", Arrays.asList("Science", "Social"));
        classSubjectMap.put("Class 3", Arrays.asList("Computer", "Nepali"));

        studentList = service.getAll();
    }

    public void save() {
        service.save(student);
        student = new Student(); // clear form
        studentList = service.getAll(); // refresh table
    }

    public void delete(Student student) {
        service.delete(student);
        studentList = service.getAll();
    }

    public void edit(Student s) {
        this.student = s;
        subjectList = classSubjectMap.get(student.getStudentClass());
    }

    public void onClassChange() {
        if (student.getStudentClass() != null) {
            subjectList = classSubjectMap.get(student.getStudentClass());
        } else {
            subjectList = new ArrayList<>();
        }
    }

    // Getters and Setters (auto generate in IntelliJ)

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<String> getClassList() {
        return classList;
    }

    public void setClassList(List<String> classList) {
        this.classList = classList;
    }

    public List<String> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<String> subjectList) {
        this.subjectList = subjectList;
    }

    public Map<String, List<String>> getClassSubjectMap() {
        return classSubjectMap;
    }

    public void setClassSubjectMap(Map<String, List<String>> classSubjectMap) {
        this.classSubjectMap = classSubjectMap;
    }

    public StudentService getService() {
        return service;
    }

    public void setService(StudentService service) {
        this.service = service;
    }
}
