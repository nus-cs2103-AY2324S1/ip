package pooh;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles storage-related operations for the Pooh chatbot.
 * <p>
 * This class is responsible for reading and writing tasks to and from a file.
 * </p>
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new Storage instance.
     *
     * @param filePath The path of the file to read from and write to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by {@code filePath}.
     *
     * @return A list of tasks loaded from the file.
     * @throws LoadTasksException If there is an error reading the file.
     */
    public List<Task> loadTasks() throws LoadTasksException {
        File file = new File(this.filePath);
        List<Task> taskList = new ArrayList<Task>();
        try {
            if (file.exists()) {
                BufferedReader fileReader = new BufferedReader(new FileReader(file));
                String line = fileReader.readLine();

                while (line != null) {
                    Task task = Task.readTaskFromFile(line);
                    taskList.add(task);
                    line = fileReader.readLine();
                }

                fileReader.close();
            } else {
                boolean fileCreated = file.createNewFile();
            }
        } catch (IOException ex) {
            throw new LoadTasksException();
        }
        return taskList;
    }

    /**
     * Writes the current tasks to the file specified by {@code filePath}.
     *
     * @param taskList The list of tasks to write.
     * @throws WriteTasksException If there is an error writing to the file.
     */
    public void writeTask(TaskList taskList) throws WriteTasksException {
        try (FileWriter fileWriter = new FileWriter("pooh.txt")) {
            for (int i = 0; i < taskList.getSize(); i++) {
                Task task = taskList.getTask(i);
                fileWriter.write(task.writeTaskToFile() + "\n");
            }
        } catch (IOException ex) {
            throw new WriteTasksException();
        } catch (InvalidTaskException e) {
            throw new RuntimeException(e);
        }
    }

}
