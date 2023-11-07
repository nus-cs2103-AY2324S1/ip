package duke;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * A copy of the Storage class that is used to perform tests in its own environment
 * Saves/loads tasks from a data file on shutdown/startup.
 * Handles data manipulation
 */
public class TestStorage {

    private static ArrayList<Task> taskArray = new ArrayList<>();

    /**
     * Loads tasks from text if it exists
     */
    public TestStorage(String text) {
        for (String task : text.split("/;/")) {
            String[] taskDetails = task.split("/%-%/");
            taskArray.add(textToTask(taskDetails));
        }
    }

    /**
     * Parse a string array into a task
     *
     * @param taskDetails A string array containing information about the task (class, dates if applicable)
     * @return A task containing add the information indicated by the text
     */
    private Task textToTask(String[] taskDetails) {
        Task savedTask;

        switch(taskDetails[0]) {
            case "T":
                if (taskDetails.length < 4) {
                    savedTask = new ToDo(taskDetails[2]);
                } else {
                    savedTask = new ToDo(taskDetails[2], taskDetails[3].split("/%t%/"));
                }
                break;
            case "D":
                if (taskDetails.length < 5) {
                    savedTask = new Deadline(taskDetails[2], taskDetails[3]);
                } else {
                    savedTask = new Deadline(taskDetails[2], taskDetails[4].split("/%t%/"), taskDetails[3]);
                }
                break;
            case "E":
                if (taskDetails.length < 6) {
                    savedTask = new Event(taskDetails[2], taskDetails[3], taskDetails[4]);
                } else {
                    savedTask = new Event(taskDetails[2], taskDetails[5].split("/%t%/"),
                            taskDetails[3], taskDetails[4]);
                }
                break;
            default:
                if (taskDetails.length < 3) {
                    savedTask = new Task(taskDetails[0]);
                } else {
                    savedTask = new Task(taskDetails[0], taskDetails[2].split("/%t%/"));
                }
        }
        if (Integer.parseInt(taskDetails[1]) == 1) {
            savedTask.markAsDone();
        }

        return savedTask;
    }

    /**
     * Append task to array
     *
     * @param t The task to be added
     */
    public void appendTask(Task t) {
        taskArray.add(t);
    }

    /**
     * List all tasks and their status
     *
     * @return A string containing information about the tasks
     */
    public String list() {
        int count = 1;
        String out = "";
        for (Task task : taskArray) {
            if (task == null) break;
            out += count++ + ". " + task.toString() + "\n";
        }

        // Assert there should not be more tasks listed than the size of the task array
        assert (count <= taskArray.size() + 1);

        return out;
    }

    /**
     * Return a list of tasks that contain a keyphrase
     *
     * @param search The keyphrase to search against
     * @return A long string containing a list of tasks that have the keyphrase
     */
    public String find(String search) {
        int count = 1;
        String out = "";
        for (Task task : taskArray) {
            if (task == null)
                break;
            if (!task.description.contains(search))
                continue;
            out += count++ + ". " + task.toString() + "\n";
        }

        // Assert there should not be more tasks listed than the size of the task array
        assert (count <= taskArray.size() + 1);

        return out;
    }

    /**
     * Gets the specified task
     *
     * @param index Array index of task
     * @return The specified task
     */
    public Task get(int index) {
        return taskArray.get(index);
    }

    /**
     * Delete the specified task
     *
     * @param index array index of task
     */
    public void delete(int index) throws NumberFormatException, IndexOutOfBoundsException {
        // Assert that the task being deleted cannot be null
        assert (taskArray.get(index) != null);

        taskArray.remove(index);
    }

    /**
     * Add a tag to a task
     *
     * @param index The specified task
     * @param tag The tag to add to the task
     */
    public void tag(int index, String tag) throws NumberFormatException, IndexOutOfBoundsException {
        taskArray.get(index).addTag(tag);
    }
}
