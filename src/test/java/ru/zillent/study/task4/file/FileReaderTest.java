package ru.zillent.study.task4.file;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.zillent.study.task4.service.DTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

    @Test
    void read() {
        FileReader fileReader = new FileReader();
        List<DTO> dtoList = fileReader.read();
        Assertions.assertTrue(dtoList.isEmpty());
        fileReader.setFolderPath("input");
        dtoList = fileReader.read();
        Assertions.assertFalse(dtoList.isEmpty());
        DTO dto = dtoList.get(0);
        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getLogin());
        Assertions.assertNotNull(dto.getFio());
        Assertions.assertNotNull(dto.getApplication());
    }
}