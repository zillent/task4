package ru.zillent.stydy.task4.fix;

import org.springframework.stereotype.Component;
import ru.zillent.stydy.task4.service.DTO;
import ru.zillent.stydy.task4.service.DataFixer;

@Component
public class DateFixer implements DataFixer {
    @Override
    public DTO fix(DTO dto) {
        if (dto == null) return null;
        if (dto.getAccessDate() != null) return dto;
        System.out.println("No access date: "+dto+" !!!");
        return null;
    }
}
