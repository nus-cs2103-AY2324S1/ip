package brandon.chatbot.storage;

import java.nio.file.Files;
import java.nio.file.Path;

import brandon.chatbot.tasks.Task;
import brandon.chatbot.tasks.TaskList;

/**
 * Represents a storage through which the program stores its output.
 */
public class Storage {
    private Path path;

    public Storage(Path path) {
        this.path = path;
    }

    /**
     * Saves tasks in the TaskList to the local storage.
     * @param tasks the tasks stored in the TaskList object.
     */
    public void save(TaskList tasks) {
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
