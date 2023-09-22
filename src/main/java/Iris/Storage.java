package iris;

import java.io.*;
import java.util.ArrayList;

/**
 * A class that handles the loading and saving of tasks to a file.
 */
public class Storage {
    /**
     * The file path where tasks are stored.
     */
    private final String filePath;

    /**
     * Constructor for the Storage class.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from a file.
     *
     * @return An ArrayList of tasks loaded from the file.
     */
    public ArrayList<Task> loadTask() throws LoadTaskException {
        File file = new File(filePath);
        ArrayList<Task> taskList = new ArrayList<>();

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
            throw new LoadTaskException();
        }
        return taskList;
    }

    /**
     * Writes tasks to a file.
     *
     * @param taskList The list of tasks to be written to file.
     */
    public void writeTask(TaskList taskList) throws WriteTaskException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            for (int i = 1; i <= taskList.getSize(); i++) {
                Task task = taskList.getTask(i);
                fileWriter.write(task.writeTaskToFile() + "\n");
            }
        } catch (IOException e) {
            throw new WriteTaskException();
        } catch (InvalidTaskException e) {
            throw new RuntimeException(e);
        }
    }
}
