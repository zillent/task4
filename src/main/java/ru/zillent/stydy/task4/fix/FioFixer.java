package ru.zillent.stydy.task4.fix;

import org.springframework.stereotype.Component;
import ru.zillent.stydy.task4.service.DTO;
import ru.zillent.stydy.task4.service.DataFixer;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class FioFixer implements DataFixer {
    @Override
    public DTO fix(DTO dto) {
        if (dto == null) return null;
        String fio = dto.getFio();
        if (fio != null && !fio.isEmpty()) {
            fio = Arrays.stream(fio.split("\\s"))
                    .filter(s -> !s.isEmpty())
                    .map(token-> Character.toUpperCase(token.charAt(0)) +token.substring(1).toLowerCase())
                    .collect(Collectors.joining(" "));
            dto.setFio(fio);
        }
        return dto;
    }
}
