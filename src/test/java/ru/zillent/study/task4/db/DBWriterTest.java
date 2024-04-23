package ru.zillent.study.task4.db;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Limit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import ru.zillent.study.task4.service.DTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@Rollback
@RunWith(SpringRunner.class)
@SpringBootTest
class DBWriterTest {
    private final String LOGIN = "TEST_login3";
    private final String FIO = "TEST_иваНов ивАан  СиДорович";
    private final Date ACCESS_DATE = new Date();
    private final String APPLICATION = "TEST_xxx";

    @Autowired
    private DBWriter dbWriter;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void write() {
        //given
        DTO dto = new DTO();
        dto.setLogin(LOGIN);
        dto.setFio(FIO);
        dto.setApplication(APPLICATION);
        dto.setAccessDate(ACCESS_DATE);
        //when
        dbWriter.write(List.of(dto));
        //then
        User user = new User();
        user.setUsername(LOGIN);
        user.setFio(FIO);
        List<User> foundUser = userRepository.findByUsername(LOGIN);
        Assertions.assertFalse(foundUser.isEmpty());
        Assertions.assertEquals(LOGIN, foundUser.get(0).getUsername());
        Assertions.assertEquals(FIO, foundUser.get(0).getFio());
        Login login = new Login();
        login.setAccessDate(ACCESS_DATE);
        login.setApplication(APPLICATION);
        login.setUser(foundUser.get(0));
        List<Login> foundLogin = loginRepository.findByApplicationOrderByAccessDateDesc(APPLICATION);
        Assertions.assertFalse(foundLogin.isEmpty());
        Assertions.assertEquals(ACCESS_DATE, foundLogin.get(0).getAccessDate());
        Assertions.assertEquals(APPLICATION, foundLogin.get(0).getApplication());
        Assertions.assertEquals(foundUser.get(0).getId(), foundLogin.get(0).getUser().getId());
    }
}