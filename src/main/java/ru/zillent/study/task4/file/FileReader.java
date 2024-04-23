package ru.zillent.study.task4.file;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.zillent.study.task4.service.DTO;
import ru.zillent.study.task4.service.DataReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
@Getter
@Setter
public class FileReader implements DataReader {
    private String folderPath;

    public List<DTO> read() {
        if (this.folderPath == null) return List.of();
        List<DTO> DTOList = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(this.folderPath))) {
            paths.filter(Files::isRegularFile).forEach((csvPath) -> {
                try {
                    MappingIterator<DTO> importDTOMappingIterator =
                            new CsvMapper().readerWithTypedSchemaFor(DTO.class).readValues(csvPath.toFile());
                    DTOList.addAll(importDTOMappingIterator.readAll());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return DTOList;
    }
}
