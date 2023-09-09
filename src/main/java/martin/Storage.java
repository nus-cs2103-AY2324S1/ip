package martin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import martin.task.Task;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the given list of tasks to the file.
     *
     * @param tasks the list of tasks to save.
     * @throws IOException if there's any issue with writing to the file.
     */
    public void saveToFile(List<Task> tasks) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(task.toFileFormat());
        }
        
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        Files.write(path, lines, StandardOpenOption.TRUNCATE_EXISTING);
    }

    /**
     * Loads tasks from the file.
     *
     * @return a list of tasks loaded from the file.
     * @throws IOException if there's any issue with reading from the file.
     * @throws IllegalArgumentException if there's a format issue with the data.
     */
    public ArrayList<Task> loadFromFile() throws IOException, IllegalArgumentException {
        Path path = Paths.get(filePath);
        ArrayList<Task> tasks = new ArrayList<>();

        if (Files.exists(path)) {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                if (line.trim().isEmpty()) 
                    continue;
                tasks.add(Task.fromFileFormat(line));
            }
        }

        return tasks;
    }
}
