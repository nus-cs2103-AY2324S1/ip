package duke.io;

<<<<<<< HEAD
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import duke.exceptions.FileIoException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.exceptions.FileIoException;
import duke.exceptions.ErrorMessages;

public class Saver {

    private final Path completeFilePath;
    private final Path parentDirectory;

    public Saver(String filePath) {
        Path currentPath = Paths.get("").toAbsolutePath();
        this.completeFilePath = currentPath.resolve(filePath);
        this.parentDirectory = completeFilePath.getParent();
    }

    public void save(TaskList taskList) throws FileIoException {
        createParentFolderIfNotExists();
        StringBuilder record = new StringBuilder();

        for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
            Task task = taskList.getTask(i);
            record.append(task.saveString()).append(System.lineSeparator());
        }

        try {
            writeToFile(record.toString());
        } catch (IOException e) {
            throw new FileIoException(ErrorMessages.STORAGE_ERROR);
        }
    }

    private void writeToFile(String textToAdd) throws IOException {
        Files.write(completeFilePath, textToAdd.getBytes());
    }

    private void createParentFolderIfNotExists() {
        if (Files.notExists(parentDirectory)) {
            try {
                Files.createDirectories(parentDirectory);
            } catch (IOException e) {
                System.out.println("Error occurred in creating directory");
            }
=======
import duke.task.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Saver {

    private static final String FILE_PATH = "./data/duke.txt";

    public static void saveToFile(List<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                fw.write(task.toFileFormat() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
>>>>>>> e6dac5a50befda9d6de9219427da7ac43ff883ea
        }
    }
}
