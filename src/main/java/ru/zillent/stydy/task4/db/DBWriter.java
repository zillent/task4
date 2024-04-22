package ru.zillent.stydy.task4.db;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.zillent.stydy.task4.service.DTO;
import ru.zillent.stydy.task4.service.DataWriter;

import java.util.List;

@Component
@Getter
@Setter
public class DBWriter implements DataWriter {
    @Override
    public void write(List<DTO> DTOList) {
        DTOList.forEach(System.out::println);
    }
}
