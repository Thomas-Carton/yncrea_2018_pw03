package yncrea.pw03.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Student {
    @Column(name="lastname")
    private String lastname;
    @Column(name="firstname")
    private String firstname;
    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "student")
    private List<Course> courses;

    @Id
    private long id;

    public void setId(long id) {
        this.id = id;
    }

    public Student(String lastname, String firstname) {
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public Student() {
    }
}
