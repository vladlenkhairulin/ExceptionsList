package Service;

import Domain.Person;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Logger {
    private Map<String, FileWriter> listOfFiles;

    public Logger() {
        this.listOfFiles = new HashMap<>();
    }

    public void log(Person person) throws IOException {
        String lastName = person.getLastName();
        FileWriter fileWriter = listOfFiles.get(lastName);
        if (fileWriter == null) {
            String filePath = lastName + ".txt"; // Имя файла соответствует фамилии
            fileWriter = new FileWriter(filePath, true); // true нужен для того, чтобы, если такое имя файла уже существует, данные продолжали записываться в этот файл, а не перезаписывать старые данные в нём.
            listOfFiles.put(lastName, fileWriter);
        }

        String logLine = person + "\n";
        fileWriter.write(logLine);
        fileWriter.flush();
    }

    public void finish() throws IOException {
        for (FileWriter fileWriter : listOfFiles.values()) {
            fileWriter.close();
        }
    }
}