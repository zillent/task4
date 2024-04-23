package ru.zillent.study.task4.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Setter
@Getter
public class DataProcessor {
    @Autowired
    private DataReader dataReader;

    @Autowired
    private DataWriter dataWriter;

    @Autowired
    private List<DataFixer> dataFixers;

    public void doWork() {
        List<DTO> inDtoList = dataReader.read();
        List<DTO> outDtoList = new ArrayList<>();
        for (DTO dto : inDtoList) {
            for (DataFixer fixer: dataFixers) {
                dto = fixer.fix(dto);
            };
            if (dto != null) outDtoList.add(dto);
        }
        dataWriter.write(outDtoList);
    }
}