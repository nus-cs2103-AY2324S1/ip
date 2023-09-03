package chatengine;

import task.Task;
import task.TaskList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
        ensureFileExists();
    }

    private void ensureFileExists() {
        try {
            if (Files.notExists(filePath)) {
                Path parentDir = filePath.getParent();
                if (Files.notExists(parentDir)) {
                    Files.createDirectories(parentDir);
                }
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file or directory: " + e.getMessage());
        }
    }

    public void saveTasks(TaskList taskList) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : taskList.getTasks()) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        }
    }

    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromFileFormat(line);
                tasks.add(task);
            }
        }
        return tasks;
    }
}


