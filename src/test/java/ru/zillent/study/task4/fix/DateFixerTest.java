package ru.zillent.study.task4.fix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import ru.zillent.study.task4.service.DTO;

import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(OutputCaptureExtension.class)
class DateFixerTest {
    private final String LOGIN = "login3";
    private final String FIO = "иваНов ивАан  СиДорович";
    private final Date ACCESS_DATE = new Date();
    private final String APPLICATION = "xxx";

    @Test
    void fix(CapturedOutput output) throws IOException {
        DTO dto = new DTO();
        dto.setLogin(LOGIN);
        dto.setFio(FIO);
        dto.setApplication(APPLICATION);
        DTO newDto = null;
        DateFixer dateFixer = new DateFixer();
        //given
        dto.setAccessDate(ACCESS_DATE);
        //when
        newDto = dateFixer.fix(dto);
        //then
        Assertions.assertEquals(LOGIN, newDto.getLogin());
        Assertions.assertEquals(FIO, newDto.getFio());
        Assertions.assertEquals(ACCESS_DATE, newDto.getAccessDate());
        Assertions.assertEquals(APPLICATION, newDto.getApplication());
        Assertions.assertFalse(output.getAll().contains("[ERROR]"));
        //given
        dto.setAccessDate(null);
        //when
        newDto = dateFixer.fix(dto);
        //then
        Assertions.assertNull(newDto);
        Assertions.assertTrue(output.getAll().contains("[ERROR]"));
        Assertions.assertTrue(output.getAll().contains("No access date: DTO(login=login3, fio=иваНов ивАан  СиДорович, accessDate=null, application=xxx) !!!"));
    }
}