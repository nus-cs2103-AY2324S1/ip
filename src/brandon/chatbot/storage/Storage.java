package storage;

import tasks.Task;
import tasks.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Storage {
    private Path path;
    public Storage(Path path) {
        this.path = path;
    }
    public void save(TaskList tasks) throws IOException {
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            String data = "";
            for (Task task : tasks.getList()) {
                data += task.getStatus() + "\n";
            }
            Files.write(path, data.getBytes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
