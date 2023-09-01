package duke.storage;

import duke.exception.DukeException;
import duke.task.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Stores the tasks of the user.
 *
 * @author Armando Jovan Kusuma
 */
public class Storage {
    private final String DIRECTORY = "./data";
    private final String FILE_PATH = DIRECTORY + "/duke.txt";
    private File STORAGE_FILE;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Creates a new file as storage if none is created yet.
     *
     * @throws DukeException When a file cannot be created.
     */
    public void init() throws DukeException {
        try {
            File dir = new File(this.DIRECTORY);
            if (!dir.exists()) {
                dir.mkdir();
            }

            this.STORAGE_FILE = new File(this.FILE_PATH);
            if (!STORAGE_FILE.exists()) {
                STORAGE_FILE.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("OOPS !!! It seems that a new file cannot be created.");
        }
    }

    /**
     * Reads the file to retrieve the list of tasks.
     *
     * @return a TaskList containing all the tasks read from the file.
     * @throws DukeException When the process of reading the file fails.
     */
    public TaskList readFromFile() throws DukeException {
        try {
            TaskList listOfTasks = new TaskList();
            Scanner scanner = new Scanner(this.STORAGE_FILE);
            while (scanner.hasNextLine()) {
                String task = scanner.nextLine();
                String type = task.substring(1, 2);
                String mark = task.substring(4, 5);
                String description = task.substring(7);

                if (type.equals("T")) {
                    Task todo = new Todo(description);
                    if (mark.equals("X")) {
                        todo.markDone();
                    }
                    listOfTasks.add(todo);
                } else if (type.equals("E")) {
                    String[] s = description.split(" \\(from: | to: |\\)");
                    LocalDate from = LocalDate.parse(s[1], formatter);
                    LocalDate to = LocalDate.parse(s[2], formatter);
                    Task event = new Event(s[0], from.toString(), to.toString());
                    if (mark.equals("X")) {
                        event.markDone();
                    }
                    listOfTasks.add(event);
                } else if (type.equals("D")) {
                    String[] s = description.split(" \\(by: |\\)");
                    LocalDate by = LocalDate.parse(s[1], formatter);
                    Task deadline = new Deadline(s[0], by.toString());
                    if (mark.equals("X")) {
                        deadline.markDone();
                    }
                    listOfTasks.add(deadline);
                } else {
                    throw new DukeException("OOPS !!! Can't Load task from File");
                }
            }
            return listOfTasks;
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format! Please input date using the format yyyy-MM-dd");
        } catch (IOException e) {
            throw new DukeException("OOPS !!! Can't Load task from File");
        }
    }


    /**
     * Writes the list of tasks to the storage file
     *
     * @param tasks the tasks to be written back to the storage file
     * @throws DukeException When the process of writing to the file fails
     */
    public void writeToFile(TaskList tasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(STORAGE_FILE);
            for (int i = 0; i < tasks.getTaskCount(); i++) {
                Task currTask = tasks.getTask(i);
                fileWriter.write(currTask.toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("OOPS !!! Can't write to file");
        }
    }
}

