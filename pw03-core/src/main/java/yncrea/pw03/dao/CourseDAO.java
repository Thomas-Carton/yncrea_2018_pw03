package yncrea.pw03.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import yncrea.pw03.entity.Course;

import java.util.List;

public interface CourseDAO extends JpaRepository<Course,Long> {
    List<Course> findByName(String name);
}
