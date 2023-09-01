package duke.Storage;

import duke.DukeException.DukeException;
import duke.Task.Deadlines;
import duke.Task.Events;
import duke.Task.Task;
import duke.Task.ToDos;
import duke.TaskList.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Store the tasks into a file.
 */
public class Storage {
    private final String DIRECTORY = "./data";
    private final String FILE_PATH = DIRECTORY + "/duke.txt";
    private File FILE;

    /**
     * Create a new file to store the tasks if it does not exist.
     * @throws DukeException Exception to handle failed file creation.
     */
    public void createStorage() throws DukeException {
        try {
            File directory = new File(this.DIRECTORY);
            if (!directory.exists()) {
                directory.mkdir();
            }
            this.FILE = new File(this.FILE_PATH);
            if (!this.FILE.exists()) {
                this.FILE.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Failed to create a file");
        }
    }

    /**
     * Get the list of task stored in the storage.
     * @return Tasklist that contain all tasks stored in the storage.
     * @throws DukeException Exception when the chatbot cannot find the storage.
     */
    public TaskList getStorage() throws DukeException {
        try {
            TaskList tasks = new TaskList();
            Scanner sc = new Scanner(this.FILE);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                String taskType = input.split(",")[0];
                String taskMark = input.split(",")[1];
                String taskName = input.split(",")[2];
                if (taskType.equals("T")) {
                    tasks.addTask(new ToDos(taskName));
                } else if (taskType.equals("D")) {
                    tasks.addTask(new Deadlines(taskName, input.split(",")[3]));
                } else if (taskType.equals("E")) {
                    tasks.addTask(new Events(taskName, input.split(",")[3],input.split(",")[4]));
                } else {
                    throw new DukeException(" OOPS!!! Failed to load tasks from file.");
                }
                if (taskMark == "0") {
                    tasks.getTask(tasks.getNumberOfTask() - 1).changeMarkStatus(true);
                }
            }
            System.out.println(tasks.getNumberOfTask());
            return tasks;
        } catch (IOException e) {
            throw new DukeException(" OOPS!!! Failed to load tasks from file.");
        }
    }

    /**
     * Edit the storage according the new TaskList edited by the user.
     * @param tasks TaskList of Tasks created by the user.
     * @throws DukeException Exception where the chatbot failed to edit the file.
     */
    public void editStorage (TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(FILE);
            for (int i = 0; i < tasks.getNumberOfTask(); i++) {
                Task task = tasks.getTask(i);
                fw.write(task.writeString());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(" OOPS!!! Failed to write to file.");
        }
    }
}
