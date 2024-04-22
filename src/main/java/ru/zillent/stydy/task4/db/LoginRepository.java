package ru.zillent.stydy.task4.db;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface LoginRepository {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO logins (access_date, application) values(:accessDate, :application) returning id;", nativeQuery = true)
    int insertLogin(@Param("accessDate") Date accessDate, @Param("application") String application);
}
