package ru.zillent.study.task4.fix;

import org.springframework.stereotype.Component;
import ru.zillent.study.task4.service.DataFixer;
import ru.zillent.study.task4.service.DTO;
import ru.zillent.study.task4.log.LogTransformation;

import java.util.List;

@LogTransformation
@Component
public class ApplicationTypeFixer implements DataFixer {
    @Override
    public DTO fix(DTO dto) {
        if (dto == null) return null;
        String application = dto.getApplication();
        if (!List.of("web", "mobile").contains(application)) dto.setApplication("other:"+dto.getApplication());
        return dto;
    }
}
