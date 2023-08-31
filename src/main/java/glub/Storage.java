package glub;

import glub.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage handles the reading of existing tasks and saving of new tasks.
 */
public class Storage {
    /** File path of existing stored task list. */
    private String taskListPath;

    /**
     * Initialises storage object.
     * @param taskListPath Path of file containing task lists to be read.
     */
    public Storage(String taskListPath) {
        this.taskListPath = taskListPath;
    }

    /**
     * Reads tasks from file. Creates file if one does not already exist.
     * @return Task details.
     * @throws GlubException If file creation fails.
     */
    public ArrayList<String> getTaskDetails() throws GlubException{
        File taskFile = new File(this.taskListPath);
        ArrayList<String> taskDetails = new ArrayList<>();
        try {
            taskFile.createNewFile();
            Scanner scanner = new Scanner(taskFile);
            while (scanner.hasNextLine()) {
                String task = scanner.nextLine();
                taskDetails.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            throw new GlubException("Task list not found!");
        } catch (IOException ex) {
            throw new GlubException("Task list file creation failed!");
        }
        return taskDetails;
    }

    /**
     * Saves tasks into a file.
     * @param taskList List of tasks to be saved.
     */
    public void saveTasks(ArrayList<Task> taskList) {
        try {
            FileWriter writer = new FileWriter("tasks.txt", false);
            for (int i = 0; i < taskList.size(); i++) {
                writer.write(taskList.get(i).toSaveFormat());
            }
            writer.close();
        } catch (IOException e) {
            Ui.printError("Saving tasks failed.\n");
            e.printStackTrace();
        }
    }
}
