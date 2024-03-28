package dev.krutik.productservice.inheritencedemo.singletable;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("st_ur")
public interface UserRepository extends JpaRepository<User,Long> {
}
