package duke;

import duke.exceptions.DukeException;
import duke.parsers.Parser;
import duke.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Task list.
 */
public class TaskList {
    private ArrayList<Task> tasklist;

    /**
     * Instantiates a new Task list, given a file to read from.
     *
     * @param file the file
     * @throws DukeException the duke exception if file is somehow not found
     */
    TaskList(File file) throws DukeException {
        this.tasklist = new ArrayList<Task>();
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                try {
                    tasklist.add(Parser.ParseFileInfo(data));
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
        this.tasklist = new ArrayList<Task>();
    }

    /**
     * Returns the tasklist.
     *
     * @return the array list with Tasks.
     */
    public ArrayList<Task> list() {
        return tasklist;
    }
}
