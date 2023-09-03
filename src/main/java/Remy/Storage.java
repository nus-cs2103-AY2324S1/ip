package Remy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import Remy.Task.TaskList;

public class Storage {

    private Path filePath;

    /**
     * Constructs a Storage object
     * @param filePath
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads existing TaskList from hard disk.
     * If none exists, creates and returns a new TaskList.
     * @return TaskList (either newly-created or loaded)
     * @throws ChatbotException
     */
    // Solution below adapted from https://stackoverflow.com/questions/10404698/saving-arrays-to-the-hard-disk
    public TaskList load() throws ChatbotException {
        TaskList tasks;

        try {
            if (!Files.exists(this.filePath)) {
                tasks = new TaskList();
            } else {
                ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(filePath));
                tasks = (TaskList) ois.readObject();
                ois.close();

            }
            return tasks;

        } catch (IOException | ClassNotFoundException e) {
            throw new ChatbotException("error loading tasks from file, your fault lah: " + e.getMessage());
        }
    }

    /**
     * Saves current TaskList to the hard disk.
     * @param tasks TaskList to be saved.
     * @throws ChatbotException
     */
    // Solution below adapted from https://stackoverflow.com/questions/10404698/saving-arrays-to-the-hard-disk
    public void save(TaskList tasks) throws ChatbotException {
        try {
            if (!Files.exists(this.filePath.getParent())) {
                // Create parent directories if they don't exist
                Files.createDirectories(this.filePath.getParent());
            }

            ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(this.filePath));
            oos.writeObject(tasks); // writes the tasks ArrayList to the file
            oos.close();
        } catch (IOException e) {
            throw new ChatbotException("IOException saving tasks to file, confirm your fault: " + e.getMessage());
        }
    }
}
