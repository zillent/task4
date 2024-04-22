package ru.zillent.stydy.task4.service;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.Date;

@Data
@JsonPropertyOrder({"login", "fio", "accessDate", "application"})
public class DTO {
    //Логин Фамилия Имя Отчество дата_входа тип_приложения
    String login;
    String fio;
    Date accessDate;
    String application;
}
