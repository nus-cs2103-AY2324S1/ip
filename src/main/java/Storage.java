import exceptions.HachiException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    private Path currentPath;
    private static String DEFAULT_PATH = "./data/tasks.txt";


    public Storage() {
        this(DEFAULT_PATH);
    }
    public Storage(String filePath) {
        Path path = Paths.get(filePath);
        currentPath = path;
    }

    public TaskList getTaskList() throws HachiException {
        if (!Files.exists(currentPath)) {
            try {
                Files.createDirectories(currentPath.getParent());
                Files.createFile(currentPath);
                currentPath = currentPath;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            return Parser.parseTaskList(Files.readAllLines(currentPath));
        } catch (IOException e) {
            throw new HachiException("Unable to read file");
        }
    }


    private static void appendToFile(File file, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        fw.write(textToAdd);
        fw.close();
    }

    public void updateTaskFile(TaskList taskList) {
        File taskPath = currentPath.toFile();
        // clear file first
        try {
            new FileWriter(taskPath).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // add every task in current task list
        taskList.iter(task -> {
            try {
                appendToFile(taskPath, task.toData() + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


}