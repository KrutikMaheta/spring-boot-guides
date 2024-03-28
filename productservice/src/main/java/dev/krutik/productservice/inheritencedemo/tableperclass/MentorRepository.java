package dev.krutik.productservice.inheritencedemo.tableperclass;

import dev.krutik.productservice.inheritencedemo.tableperclass.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("tpc_mr")
public interface MentorRepository extends JpaRepository<Mentor, Long> {
}
