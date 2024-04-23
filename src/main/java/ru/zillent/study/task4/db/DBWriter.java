package ru.zillent.study.task4.db;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zillent.study.task4.service.DTO;
import ru.zillent.study.task4.service.DataWriter;

import java.util.List;

@Component
@Getter
@Setter
public class DBWriter implements DataWriter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public void write(List<DTO> DTOList) {
        DTOList.forEach(dto -> {
            final Integer[] id = {null};
            userRepository.findByUsername(dto.getLogin())
                    .stream()
                    .findFirst()
                    .ifPresent(user -> id[0] = user.getId());
            if (id[0] == null) {
                id[0] = userRepository.insertUser(dto.getLogin(), dto.getFio());
            }
            loginRepository.insertLogin(dto.getAccessDate(), dto.getApplication(), id[0]);
        });
    }

}
