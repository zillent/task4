package ru.zillent.study.task4.db;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {
    //@Modifying
    @Transactional
    @Query(value = "INSERT INTO logins (access_date, application, user_id) values(:accessDate, :application, :userId) returning id;", nativeQuery = true)
    int insertLogin(@Param("accessDate") Date accessDate, @Param("application") String application, @Param("userId") int userId);

    List<Login> findByApplicationOrderByAccessDateDesc(String application);
}
