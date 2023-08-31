package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.tasklist.Task;
import duke.tasklist.TaskList;

/**
 * Manages the storage of task data in the Duke application.
 * This class handles loading tasks from and saving tasks to a file.
 */
public class Storage {
    private final String filePath;
    private String home = System.getProperty("user.home");

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path for storing task data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file and returns them as a collection.
     *
     * @return A collection of tasks loaded from the file.
     * @throws DukeException If there is an error while loading tasks.
     */
    public Collection<Task> load() throws DukeException {
        boolean directoryExists = new java.io.File(home + "\\Documents\\ip\\data").exists();
        if (!directoryExists) {
            java.nio.file.Path path = java.nio.file.Paths.get(home,
                    "Desktop", "NUS", "Y2", "CS2103T", "ip", "data");
            directoryExists = java.nio.file.Files.exists(path);
            if (!directoryExists) {
                throw new DukeException("There is no 'data' folder, please create one");
            }
        }
        File f = new File(filePath);

        try {
            ArrayList<Task> lst = new ArrayList<Task>();
            if (f.exists()) {
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    String temp = s.nextLine();
                    lst.add(existTasks(temp));
                }
                return lst;
            } else {
                f.createNewFile();
                return lst;
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Creates a Task object based on the provided data string.
     *
     * @param s The data string representing a task.
     * @return A Task object based on the data string.
     */
    private Task existTasks(String s) {
        String[] temp = s.split(" \\| ");
        int n = temp.length;
        Task t;
        if (n == 2) {
            t = Task.of(temp[1]);
        } else if (n == 3) {
            t = Task.of(temp[1], LocalDate.parse(temp[2]));
        } else {
            t = Task.of(temp[1], LocalDate.parse(temp[2]), LocalDate.parse(temp[3]));
        }

        if (Objects.equals(temp[0], "1")) {
            t.mark();
        }
        return t;
    }

    /**
     * Writes the tasks from the task list to the file.
     *
     * @param lst The task list containing tasks to be saved.
     * @throws IOException If there is an error while writing to the file.
     */
    public void changeFile(TaskList lst) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw = new FileWriter(filePath, true);
        for (Task curr : lst) {
            fw.write(curr.getText() + "\n");
        }
        fw.close();
    }

    /**
     * Adds a task to the file.
     *
     * @param t The task to be added to the file.
     * @throws DukeException If there is an error while adding the task to the file.
     */
    public void addToFile(Task t) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(t.getText() + "\n");
            fw.close();
        } catch (IOException iE) {
            throw new DukeException(iE.getMessage());
        }
    }
}
