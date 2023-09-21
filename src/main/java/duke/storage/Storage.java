package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
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
    private final String FILEPATH;
    private final String ROOT = System.getProperty("user.dir");
    String FOLDER_PATH = ROOT + File.separator + "data";

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path for storing task data.
     */
    public Storage(String filePath) {
        FILEPATH = filePath;
    }

    /**
     * Loads tasks from the file and returns them as a collection.
     *
     * @return A collection of tasks loaded from the file.
     * @throws DukeException If there is an error while loading tasks.
     */
    public Collection<Task> load() throws DukeException {
        boolean directoryExists = new java.io.File(FOLDER_PATH).exists();

        if (!directoryExists) {
            File dataFolder = new File(FOLDER_PATH);
            dataFolder.mkdir();
        }
        File f = new File(FILEPATH);

        try {
            ArrayList<Task> lst = new ArrayList<>();
            if (!f.exists()) {
                f.createNewFile();
                return lst;
            }
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String temp = s.nextLine();
                lst.add(existTasks(temp));
            }
            return lst;
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
    private Task existTasks(String s) throws DukeException{
        String[] temp = s.split(" \\| ");
        int n = temp.length;
        assert n >= 3 : "Task should have at least 2 parts";
        Task t;
        try {
            if (n == 3) {
                t = Task.of(temp[2]);
            } else if (n == 4) {
                t = Task.of(temp[2], LocalDate.parse(temp[3]));
            } else if (n == 5) {
                t = Task.of(temp[2], LocalDate.parse(temp[3]), LocalDate.parse(temp[4]));
            } else {
                throw new DukeException("Error in parsing loading tasks.");
            }
        } catch ( DateTimeException e) {
            throw new DukeException("Error in parsing loading tasks");
        }

        hasMarked(temp[0], t);
        hasSnoozed(temp[1], t);

        return t;
    }

    private void hasMarked(String strMk, Task task) throws DukeException {
        if (Objects.equals(strMk, "1")) {
            task.mark();
        } else if (!Objects.equals(strMk, "0")) {
            throw new DukeException("Error in marking loading tasks");
        }
    }

    private void hasSnoozed(String strSz, Task task) throws DukeException {
        if (Objects.equals(strSz, "1")) {
            task.snooze();
        } else if (!Objects.equals(strSz, "0")) {
            throw new DukeException("Error in snoozing loading tasks");
        }
    }

    /**
     * Writes the tasks from the task list to the file.
     *
     * @param lst The task list containing tasks to be saved.
     * @throws IOException If there is an error while writing to the file.
     */
    public void changeFile(TaskList lst) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH);
        fw.write("");
        fw = new FileWriter(FILEPATH, true);
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
            FileWriter fw = new FileWriter(FILEPATH, true);
            fw.write(t.getText() + "\n");
            fw.close();
        } catch (IOException iE) {
            throw new DukeException(iE.getMessage());
        }
    }
}
