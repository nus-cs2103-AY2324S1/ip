package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;






/**
 * Encapsulates the storage of tasks in duke.txt.
 */
public class Storage {

    private File file;
    private String filePath;

    /**
     * Constructs a Storage object with the default file path.
     */
    public Storage() {
        this.filePath = "data/duke.txt";
        this.file = getFile();
    }

    private File getFile() {
        File file = new File(this.filePath);
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (Exception e) {
            new DukeException("Unable to create file: " + e.getMessage());
        }
        return file;
    }

    private static Task addTaskFromStorage(String task) {
        Task newTask = null;
        if (task.startsWith("T")) {
            newTask = ToDo.createToDoFromStorage(task);
        } else if (task.startsWith("D")) {
            newTask = Deadline.createDeadlineFromStorage(task);
        } else if (task.startsWith("E")) {
            newTask = Event.createEventFromStorage(task);
        }

        return newTask;
    }

    /**
     * Reads the tasks from duke.txt and returns them as a list of tasks.
     *
     * @return The list of tasks read from duke.txt.
     * @throws DukeException If there is an error reading from duke.txt.
     */
    public List<Task> readTasks() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String entry = sc.nextLine();
                Task newTask = addTaskFromStorage(entry);
                tasks.add(newTask);
                System.out.println(newTask);
            }
        } catch (Exception e) {
            throw new DukeException("Unable to read from storage: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Writes the given list of tasks to duke.txt.
     *
     * @param tasks The list of tasks to be written to duke.txt.
     * @throws DukeException If there is an error writing to duke.txt.
     */
    public void write(List<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks) {
                fw.write(task.toStorageString() + "\n");
            }
            fw.close();
        } catch (Exception e) {
            throw new DukeException("Unable to write to storage: " + e.getMessage());
        }
    }

    /**
     * Writes the given task to duke.txt.
     *
     * @param newTask The task to be written to duke.txt.
     * @throws DukeException If there is an error writing to duke.txt.
     */
    public void write(Task newTask) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(newTask.toStorageString() + "\n");
            fw.close();
        } catch (Exception e) {
            throw new DukeException("Unable to write to storage: " + e.getMessage());
        }
    }

}
