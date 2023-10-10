package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.parsers.Parser;
import duke.tasks.Task;

/**
 * The type Task list.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Instantiates a new Task list, given a file to read from.
     *
     * @param file the file
     * @throws DukeException the duke exception if file is somehow not found
     */
    TaskList(File file) throws DukeException {
        this.taskList = new ArrayList<Task>();
        assert file.canRead();
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                try {
                    taskList.add(Parser.parseFileInfo(data));
                } catch (DukeException e) {
                    System.out.println(e);
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("FileNotFound Error");
        }
    }

    /**
     * Instantiates a new Task list.
     */
    TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Returns the tasklist.
     *
     * @return the array list with Tasks.
     */
    public ArrayList<Task> list() {
        assert taskList != null;
        return taskList;
    }

    /**
     * Checks if list contains a task.
     *
     * @param key the key
     * @return the array list
     */
    public ArrayList<Task> contains(String key) {
        assert taskList != null;
        ArrayList<Task> temp = new ArrayList<>(this.taskList);
        temp.removeIf(t -> !t.containsStr(key));
        return temp;
    }

    /**
     * Checks if task is in the date.
     *
     * @param date the date
     * @return the array list
     */
    public ArrayList<Task> containsDate(LocalDate date) {
        assert taskList != null;
        ArrayList<Task> temp = new ArrayList<>(this.taskList);
        temp.removeIf(t -> !t.checkDate(date));
        return temp;
    }
}
