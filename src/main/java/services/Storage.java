package services;

import exceptions.DukeException;
import services.parser.FileParser;
import tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Storage {
    private static final String FILE_PATH = "./data/duke.txt";

    public Storage() {
    }

    private File createHardDisk() {
        File file = new File(FILE_PATH);
        File directory = file.getParentFile();

        if (!directory.exists()) {
            directory.mkdirs();
        }

        return file;
    }

    public void saveTasksToFile(TaskList tasks) throws IOException {
        File file = createHardDisk();

        FileWriter fileWriter = new FileWriter(file);
        for (Task task : tasks.getTasks()) {
            fileWriter.write(task.toFileString() + "\n");
        }
        fileWriter.close();
    }

    public void loadTasksFromFile(TaskList tasks) throws IOException {
        File file = createHardDisk();

        List<String> list = Files.readAllLines(file.toPath());
        for (String taskString : list) {
            try {
                Task task = FileParser.parseTaskFromString(taskString);
                tasks.addTask(task);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
