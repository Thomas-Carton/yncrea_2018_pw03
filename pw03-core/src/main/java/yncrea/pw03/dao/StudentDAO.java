package yncrea.pw03.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import yncrea.pw03.entity.Student;

import java.util.List;

public interface StudentDAO extends JpaRepository<Student, Long> {
    List<Student> findByLastName(String lastname);
}
