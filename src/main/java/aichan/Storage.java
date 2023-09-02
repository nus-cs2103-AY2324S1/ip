package aichan;

import aichan.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage which deals with loading tasks and saving tasks.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a storage object.
     *
     * @param filePath File path for storing and loading tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the tasks to the file.
     *
     * @param tasks TaskList contains current tasks.
     * @throws AiChanException If an error occurs when saving tasks.
     */
    public void save(TaskList tasks) throws AiChanException {
        try {
            File file = new File(this.filePath);
            FileWriter filewriter = new FileWriter(file);

            File directory = file.getParentFile();
            if (directory != null && !directory.exists()) {
                directory.mkdirs();
            }

            for (Task task : tasks.getTasks()) {
                String line = task.toFileLine();
                if (line != null) {
                    filewriter.write(line + "\n");
                }
            }
            filewriter.close();
        } catch (IOException e) {
            throw new AiChanException("Error saving the tasks into the file.");
        }
    }

    /**
     * Loads tasks which are stored in the file.
     *
     * @return ArrayList which contains the tasks.
     * @throws AiChanException If an error occurs when loading tasks.
     */
    public ArrayList<Task> load() throws AiChanException {
        ArrayList<Task> arrTask = new ArrayList<>();

        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                return arrTask;
            }
            Scanner scn = new Scanner(file);
            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                if (line.isEmpty()) {
                    break;
                }
                Task task = Task.stringToTask(line);
                if (task != null) {
                    arrTask.add(task);
                }
            }
            scn.close();
        } catch (IOException e) {
            throw new AiChanException("Error loading tasks from file.");
        }
        return arrTask;
    }

}
