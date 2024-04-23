package ru.zillent.study.task4.db;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE users set username=:username where id=:id", nativeQuery = true)
    int updateUserSetUsername(@Param("username") String username, @Param("id") Integer id);

//    @Modifying - не работает с insert returning
    @Transactional
    @Query(value = "INSERT INTO users (username, fio) values(:username, :fio) returning id;", nativeQuery = true)
    int insertUser(@Param("username") String username, @Param("fio") String fio);

    List<User> findByUsername(String username);
}
