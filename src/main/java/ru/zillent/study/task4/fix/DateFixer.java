package ru.zillent.study.task4.fix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.zillent.study.task4.service.DataFixer;
import ru.zillent.study.task4.log.LogTransformation;
import ru.zillent.study.task4.service.DTO;

@LogTransformation
@Component
public class DateFixer implements DataFixer {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateFixer.class);
    @Override
    public DTO fix(DTO dto) {
        if (dto == null) return null;
        if (dto.getAccessDate() != null) return dto;
        LOGGER.error("No access date: {} !!!", dto);
        return null;
    }
}
