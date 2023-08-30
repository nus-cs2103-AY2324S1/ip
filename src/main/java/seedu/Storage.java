package seedu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    private final Path filePath;

    /**
     * Stores the file of the tasks
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public TaskList load() throws Exception {
        try {
            List<String> tasks = Files.readAllLines(filePath);
            return TaskList.getFromFile(tasks);
        } catch(IOException e) {
            throw new IOException("Error writing to file: " + filePath);
        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void save(TaskList tasks) throws IOException {
        try {
            List<String> encodedTasks = TaskList.encode(tasks);
            Files.write(filePath, encodedTasks);
        } catch (IOException ioe) {
            throw new IOException("Error writing to file: " + filePath);
        }
    }
}
