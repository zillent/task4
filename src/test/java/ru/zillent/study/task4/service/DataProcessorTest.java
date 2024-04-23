package ru.zillent.study.task4.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class DataProcessorTest {
    private final String LOGIN = "login3";
    private final String FIO = "иваНов ивАан  СиДорович";
    private final Date ACCESS_DATE = new Date();
    private final String APPLICATION = "xxx";

    @MockBean
    private DataReader dataReader;

    @MockBean
    private DataWriter dataWriter;

    @MockBean
    private List<DataFixer> dataFixers;

    @Autowired
    private DataProcessor dataProcessor;

    @Test
    void doWork() {
        //given
        DTO dto = new DTO();
        dto.setLogin(LOGIN);
        dto.setFio(FIO);
        dto.setAccessDate(ACCESS_DATE);
        dto.setApplication(APPLICATION);
        DTO dto1 = new DTO();
        dto1.setLogin(LOGIN);
        dto1.setFio(FIO);
        dto1.setAccessDate(null);
        dto1.setApplication(APPLICATION);
        DTO dto2 = new DTO();
        dto2.setLogin(LOGIN);
        dto2.setFio(FIO);
        dto2.setAccessDate(ACCESS_DATE);
        dto2.setApplication(APPLICATION);
        List<DTO> dtoList = List.of(dto, dto1, dto2);
        doReturn(dtoList).when(dataReader).read();
        //when
        dataProcessor.doWork();
        //then
        verify(dataWriter).write(argThat(args -> {
            //dto1 with null date is not valid and does not get in output
            Assertions.assertEquals(2, args.size());
            Assertions.assertEquals("Иванов Иваан Сидорович", args.get(0).getFio());
            Assertions.assertEquals(LOGIN, args.get(0).getLogin());
            Assertions.assertEquals("other:"+APPLICATION, args.get(0).getApplication());
            Assertions.assertEquals(ACCESS_DATE, args.get(0).getAccessDate());
            return true;
        }));
    }
}