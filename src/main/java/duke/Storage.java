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
 * Stores the list of tasks.
 * Saves/loads tasks from a data file on shutdown/startup.
 * Handles data manipulation
 */
public class Storage {

    private static ArrayList<Task> taskArray = new ArrayList<>();

    /**
     * Loads tasks from data file if it exists
     */
    public Storage() {
        initializeDirectory();
        processTextFile();
    }

    /**
     * Get the directory of the chatbot's data file, or create a new directory if it doesn't exist.
     */
    private void initializeDirectory() {
        try {
            // Get directory of data
            Path path = Paths.get("./data");

            // Assert that the file is located in the correct file hierarchy, and the file has the correct name
            assert (path.getParent().getParent() == null);
            assert (path.getName(1).toString().equals("data"));

            // Make new directory if it doesn't exist
            if (!Files.exists(path)) {
                Files.createDirectories(path);
                System.out.println("Directory is created!");
            }

        } catch (IOException e) {
            System.err.println("Failed to create directory!" + e.getMessage());
        }
    }

    /**
     * Extract the task data from the text file, or create a new text file if it doesn't exist.
     */
    private void processTextFile() {
        try {
            FileReader fr = new FileReader("./data/duke.txt");
            int c;
            String savedTasks = "";
            while ((c = fr.read()) != -1) {
                savedTasks += (char) c;
            }

            // Assert that fr.read is equals -1, meaning all characters of the text file is read
            assert (fr.read() == -1);

            String[] taskList = new String[100];
            for (String task : savedTasks.split(";")) {
                String[] taskDetails = task.split("/");
                taskArray.add(textToTask(taskDetails));
            }

        } catch (Exception e) {
            // if the file is not found, create a new text file
            new File("./data/duke.txt");
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
                savedTask = new ToDo(taskDetails[2]);
                break;
            case "D":
                savedTask = new Deadline(taskDetails[2], taskDetails[3]);
                break;
            case "E":
                savedTask = new Event(taskDetails[2], taskDetails[3], taskDetails[4]);
                break;
            default:
                savedTask = new Task(taskDetails[0]);
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
      
        // Assert there should be more tasks listed than the size of the task array
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

        // Assert there should be more tasks listed than the size of the task array
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
     * Write the contents of duke.Storage into file
     */
    public void write() throws IOException {
        FileWriter fw = new FileWriter("./data/duke.txt");
        String out = "";
        for (Task taskToSave : taskArray) {
            out += taskToText(taskToSave);
        }

        fw.write(out);
        fw.close();
    }

    /**
     * Convert task information into parsable text
     *
     * @param taskToSave The Task to be converted into text
     * @return String containing specific information about the task
     */
    private String taskToText(Task taskToSave) {
        String taskType;
        String taskAppendices = "";

        // Parse based on class of task
        if (taskToSave instanceof ToDo) {
            taskType = "T/";
            taskAppendices = "/" + taskToSave.description;
        } else if (taskToSave instanceof Deadline) {
            taskType = "D/";
            taskAppendices = "/"  + taskToSave.description + "/" + ((Deadline) taskToSave).by;
        } else if (taskToSave instanceof Event) {
            taskType = "E/";
            taskAppendices = "/" + taskToSave.description + "/" +
                    ((Event) taskToSave).from + "/" + ((Event) taskToSave).to;
        } else {
            taskType = taskToSave.description + "/";
        }

        return taskType + (taskToSave.isDone ? 1 : 0) + taskAppendices + ";";
    }
}
