package bob.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import bob.data.exception.DukeException;
import bob.data.task.DeadlineTask;
import bob.data.task.EventTask;
import bob.data.task.Task;
import bob.data.task.ToDoTask;

/**
 * Represents the logic for reading/writing the task data to the file.
 */
public class Storage {
    /** The file for reading/writing the tasks from/to */
    private File file;

    /**
     * Constructs the storage which will create a new directory called "data" and a new file called "duke.txt" in data
     * if not already created.
     */
    public Storage() {
        try {
            File dataDirectory = new File("./data");
            dataDirectory.mkdir();
            assert dataDirectory.exists() : "data directory should exist";
            File taskFile = new File("./data/duke.txt");
            assert taskFile.exists() : "taskFile should exist";
            if (taskFile.createNewFile()) {
                System.out.println("Created new file to store your tasks!");
            } else {
                System.out.println("Existing task file exists. ");
            }
            this.file = taskFile;
        } catch (IOException e) {
            System.out.println("Unable to create file.");
            e.printStackTrace();
        }
    }

    /**
     * Returns the file that was created during the instantiation of this Storage object.
     *
     * @return The file where the tasks are being read from or written to.
     */
    public File getFile() {
        return this.file;
    }

    /**
     * Reads the tasks in the datafile and writes it to the ArrayList for use by the chatbot.
     * @param list The ArrayList to store the tasks in.
     * @throws FileNotFoundException if file cannot be found.
     * @throws DukeException if the EventTask is instantiated with wrong dates.
     */
    public void readFromFile(ArrayList<Task> list) throws FileNotFoundException, DukeException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String storedTask = scanner.nextLine();
            String[] taskArray = storedTask.split(",");
            Task task;
            if (taskArray[0].startsWith("Todo")) {
                task = new ToDoTask(taskArray[2]);
            } else if (taskArray[0].startsWith("Deadline")) {
                task = new DeadlineTask(taskArray[2], taskArray[3]);
            } else if (taskArray[0].startsWith("Event")) {
                task = new EventTask(taskArray[2], taskArray[3], taskArray[4]);
            } else {
                throw new DukeException("Corrupt file.");
            }
            if ((taskArray[1]).equals("1")) {
                task.setDone();
            }
            list.add(task);
        }
        scanner.close();
    }

    /**
     * Changes the class field file to a new location.
     * @param file The new file to set storage to.
     */
    protected void setFile(File file) {
        this.file = file;
    }
}
