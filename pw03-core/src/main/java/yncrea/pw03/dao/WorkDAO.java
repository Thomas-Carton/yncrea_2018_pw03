package yncrea.pw03.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import yncrea.pw03.entity.Work;

import java.util.List;

public interface WorkDAO extends JpaRepository<Work,Long> {
    List<Work> findByName(String name);
}
