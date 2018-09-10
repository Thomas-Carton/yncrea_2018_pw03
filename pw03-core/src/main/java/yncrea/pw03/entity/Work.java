package yncrea.pw03.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Work {
    @Column(name="name")
    private String name;
    @Column(name="course")
    private Course course;
    @Column(name="grade")
    private int grade;

    @Id
    private long id;

    public void setId(long id) {
        this.id = id;
    }

    public Work() {
    }

    public Work(String name, Course course, int grade) {
        this.name = name;
        this.course = course;
        this.grade = grade;
    }
}
