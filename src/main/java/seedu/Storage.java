package seedu;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    private final Path filePath;
    private String filename;

    /**
     * Stores the file of the tasks
     */
    public Storage(String filePath) {
        this.filename = filePath;

        File file = new File(filename);
        File parentDirectory = file.getParentFile();

        if (!parentDirectory.exists()) {
            parentDirectory.mkdirs();
        }

        this.filePath = Paths.get(filePath);
        if(file.exists()) {
            return;
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

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
