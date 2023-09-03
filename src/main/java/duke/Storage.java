package duke;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private Path dataPath;

    public Storage(String fileName) {
        createFile(fileName);
        this.dataPath = Path.of(".", "data", fileName);
    }

    private void createFile(String fileName) {
        try {
            Path dataDirectoryPath = Path.of(".", "data");

            // Create directory because it doesn't exist
            if (!Files.exists(dataDirectoryPath)) {
                Files.createDirectories(dataDirectoryPath);
            }

            Path dataPath = dataDirectoryPath.resolve(fileName);

            // Create duke.txt if it doesn't exist
            if (!Files.exists(dataPath)) {
                Files.createFile(dataPath);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            List<String> fileLines = Files.readAllLines(dataPath);
            for (String line : fileLines) {
                Task task = Parser.convertToTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException | DukeException e) {
            System.err.println(e.getMessage());
        }
        return tasks;
    }

    public void saveToFile(TaskList updatedTasks) {
        try {
            if (updatedTasks == null) {
                return;
            }

            List<String> lines = new ArrayList<>();

            for (Task task : updatedTasks.tasks) {
                lines.add(task.taskToFileString());
            }

            Files.write(dataPath, lines);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}