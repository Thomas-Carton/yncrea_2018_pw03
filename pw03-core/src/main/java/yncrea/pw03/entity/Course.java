package yncrea.pw03.entity;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.List;
@Entity
public class Course {

    @Column(name="validated")
    private boolean validated;
    @Column(name="name")
    private String name;
    @ManyToOne
    private Student student;
    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "course")
    private List<Work> works;

    @Id
    private long id;

    public void setId(long id) {
        this.id = id;
    }

    public Course() {
    }

    public Course(String name, Student student) {
        this.name = name;
        this.student = student;
    }
}
