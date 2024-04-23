package ru.zillent.study.task4.fix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.zillent.study.task4.service.DTO;

import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class FioFixerTest {
    private final String LOGIN = "login3";
    private final String FIO = "иваНов ивАан  СиДорович";
    private final Date ACCESS_DATE = new Date();
    private final String APPLICATION = "xxx";

    @Test
    void fix() throws IOException {
        //given
        DTO dto = new DTO();
        dto.setLogin(LOGIN);
        dto.setFio(FIO);
        dto.setAccessDate(ACCESS_DATE);
        dto.setApplication(APPLICATION);
        DTO newDto = null;
        FioFixer fioFixer = new FioFixer();
        //when
        newDto = fioFixer.fix(dto);
        //then
        Assertions.assertEquals(LOGIN, newDto.getLogin());
        Assertions.assertEquals("Иванов Иваан Сидорович", newDto.getFio());
        Assertions.assertEquals(ACCESS_DATE, newDto.getAccessDate());
        Assertions.assertEquals(APPLICATION, newDto.getApplication());
    }
}