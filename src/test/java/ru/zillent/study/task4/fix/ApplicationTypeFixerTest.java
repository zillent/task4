package ru.zillent.study.task4.fix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.zillent.study.task4.service.DTO;

import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTypeFixerTest {
    private final String LOGIN = "login3";
    private final String FIO = "иванов иван  Сидорович";
    private final Date ACCESS_DATE = new Date();

    @Test
    void fix() throws IOException {
        //common
        DTO dto = new DTO();
        dto.setLogin(LOGIN);
        dto.setFio(FIO);
        dto.setAccessDate(ACCESS_DATE);
        DTO newDto = null;
        ApplicationTypeFixer applicationTypeFixer = new ApplicationTypeFixer();
        //given
        dto.setApplication("web");
        //when
        newDto = applicationTypeFixer.fix(dto);
        //then
        Assertions.assertEquals(LOGIN, newDto.getLogin());
        Assertions.assertEquals(FIO, newDto.getFio());
        Assertions.assertEquals(ACCESS_DATE, newDto.getAccessDate());
        Assertions.assertEquals("web", newDto.getApplication());
        //given
        dto.setApplication("mobile");
        //when
        newDto = applicationTypeFixer.fix(dto);
        //then
        Assertions.assertEquals(LOGIN, newDto.getLogin());
        Assertions.assertEquals(FIO, newDto.getFio());
        Assertions.assertEquals(ACCESS_DATE, newDto.getAccessDate());
        Assertions.assertEquals("mobile", newDto.getApplication());
        //given
        dto.setApplication("xxx");
        //when
        newDto = applicationTypeFixer.fix(dto);
        //then
        Assertions.assertEquals(LOGIN, newDto.getLogin());
        Assertions.assertEquals(FIO, newDto.getFio());
        Assertions.assertEquals(ACCESS_DATE, newDto.getAccessDate());
        Assertions.assertEquals("other:xxx", newDto.getApplication());
    }
}