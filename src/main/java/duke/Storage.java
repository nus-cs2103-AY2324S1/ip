package duke;

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
        try {
            // Get directory of data
            Path path = Paths.get("./data");

            // Make new directory if it doesn't exist
            if (!Files.exists(path)) {
                Files.createDirectories(path);
                System.out.println("Directory is created!");
            }

        } catch (IOException e) {
            System.err.println("Failed to create directory!" + e.getMessage());
        }

        try {
            FileReader fr = new FileReader("./data/duke.txt");
            int c;
            String savedTasks = "";
            while ((c=fr.read()) != -1) {
                savedTasks += (char) c;
            }

            String[] taskList = new String[100];
            for (String task : savedTasks.split(";")) {
                String[] taskDetails = task.split("/");
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
                taskArray.add(savedTask);
            }
        } catch (IOException fe) {
            System.out.println("File not found, creating new text file...");
        }
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
        return out;
    }

    /**
     * Gets the specified task
     *
     * @param index array index of task
     * @return the specified task
     */
    public Task get(int index) {
        return taskArray.get(index);
    }

    /**
     * Delete the specified task
     */
    public void delete(int index) throws NumberFormatException, IndexOutOfBoundsException {
        taskArray.remove(index);
    }

    /**
     * Write the contents of duke.Storage into file
     */
    public void write() throws IOException {
        FileWriter fw = new FileWriter("./data/duke.txt");
        String out = "";

        for (Task taskToSave : taskArray) {
            String taskType;
            String taskAppendices = "";
            if (taskToSave instanceof ToDo) {
                taskType = "T/";
                taskAppendices = "/" + taskToSave.description;
            } else if (taskToSave instanceof Deadline) {
                taskType = "D/";
                taskAppendices = "/"  + taskToSave.description + "/" + ((Deadline) taskToSave).by;
            } else if (taskToSave instanceof  Event) {
                taskType = "E/";
                taskAppendices = "/" + taskToSave.description + "/" +
                        ((Event) taskToSave).from + "/" + ((Event) taskToSave).to;
            } else {
                taskType = taskToSave.description + "/";
            }
            out += taskType + (taskToSave.isDone ? 1 : 0) + taskAppendices + ";";
        }

        fw.write(out);
        fw.close();
    }
}
