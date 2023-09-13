package brandon.chatbot.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import brandon.chatbot.tasks.Task;
import brandon.chatbot.tasks.TaskList;

/**
 * Stores the task list on the hard disk.
 */
public class Storage {
    private Path path;
    public Storage(Path path) {
        this.path = path;
    }

    /**
     * Saves tasks in the task list by creating a file.
     * @param tasks
     * @throws IOException
     */
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
