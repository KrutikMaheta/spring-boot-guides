package dev.krutik.productservice.inheritencedemo.tableperclass;

import dev.krutik.productservice.inheritencedemo.tableperclass.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("tpc_ur")
public interface UserRepository extends JpaRepository<User,Long> {
}
