package ru.zillent.study.task4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.zillent.study.task4.file.FileReader;
import ru.zillent.study.task4.service.DataProcessor;

@SpringBootApplication
public class Task4Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Task4Application.class, args);
		DataProcessor dataProcessor = ctx.getBean(DataProcessor.class);
		((FileReader) dataProcessor.getDataReader()).setFolderPath("input");
		dataProcessor.doWork();
	}
}
