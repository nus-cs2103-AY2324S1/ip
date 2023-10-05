package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.*;

/**
 * Stores the list of tasks.
 *
 * @author Angky Akdi Frandy Putrakelana
 */
public class Storage {
    private final File FILE;

    /**
     * Construct a storage and make a new file if no file exists yet.
     *
     * @throws DukeException If the file cannot be made.
     */
    public Storage() throws DukeException {
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            this.FILE = new File("./data/duke");
            if (!FILE.exists()) {
                this.FILE.createNewFile();
            }
        } catch (Exception e) {
            throw new DukeException("Cannot Make File :'(");
        }
    }

    /**
     * Reads and loads the file.
     *
     * @return an Array
     * @throws DukeException If the file cannot be read.
     */
    public TaskList load() throws DukeException {
        TaskList taskList = new TaskList();
        try {
            Scanner sc = new Scanner(FILE);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                String type = input.substring(0, 1);
                String done = input.substring(4, 5);
                String description = input.substring(8);
                if (type.equalsIgnoreCase("T")) {
                    taskList.add(new Todo(description));
                } else if (type.equalsIgnoreCase("D")) {
                    taskList.add(new Deadline(description.replaceFirst("\\|", "/by")));
                } else if (type.equalsIgnoreCase("E")) {
                    taskList.add(new Event(description.replaceFirst("\\|", "/from").replaceFirst("\\|", "/to")));
                } else if (type.equalsIgnoreCase("F")) {
                    taskList.add(new FixedTask(description.replaceFirst("\\|", "/need")));
                }
                if (done.equalsIgnoreCase("X")) {
                    taskList.getTask(taskList.getSize() - 1).mark();
                }

            }
            return taskList;
        } catch (IOException e) {
            throw new DukeException("Cannot Read File :'(");
        }
    }

    /**
     * Rewrite the file after a tasks being added or edited.
     *
     * @param tasks the TaskList that will be stored.
     * @throws DukeException If the file cannot be written.
     */
    public void writeFile(TaskList tasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(this.FILE);
            for (int i = 0; i < tasks.getSize(); i++) {
                fileWriter.write(tasks.getTaskInput(i) + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Cannot Write File :'(");
        }
    }
}
