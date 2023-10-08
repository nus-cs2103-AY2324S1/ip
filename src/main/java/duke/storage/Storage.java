package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Stores the tasks of the user.
 *
 * @author Armando Jovan Kusuma
 */
public class Storage {
    private String directory = "./data";
    private String filePath = directory + "/duke.txt";
    private File storageFile;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Creates a new file as storage if none is created yet.
     *
     * @throws DukeException When a file cannot be created.
     */
    public void init() throws DukeException {
        try {
            File dir = new File(this.directory);
            if (!dir.exists()) {
                dir.mkdir();
            }

            this.storageFile = new File(this.filePath);
            if (!storageFile.exists()) {
                storageFile.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("OOPS! It seems that a new file cannot be created.");
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
            Scanner scanner = new Scanner(this.storageFile);
            while (scanner.hasNextLine()) {
                String task = scanner.nextLine();
                String type = task.substring(1, 2);
                String mark = task.substring(4, 5);
                String description = task.substring(7);
                switch(type) {
                case "T":
                    Task todo = new Todo(description);
                    if (mark.equals("X")) {
                        todo.markDone();
                    }
                    listOfTasks.add(todo);
                    break;
                case "E":
                    String[] eventSplit = description.split(" \\(from: | to: |\\)");
                    LocalDate from = LocalDate.parse(eventSplit[1], formatter);
                    LocalDate to = LocalDate.parse(eventSplit[2], formatter);
                    Task event = new Event(eventSplit[0], from.toString(), to.toString());
                    if (mark.equals("X")) {
                        event.markDone();
                    }
                    listOfTasks.add(event);
                    break;
                case "D":
                    String[] deadlineSplit = description.split(" \\(by: |\\)");
                    LocalDate by = LocalDate.parse(deadlineSplit[1], formatter);
                    Task deadline = new Deadline(deadlineSplit[0], by.toString());
                    if (mark.equals("X")) {
                        deadline.markDone();
                    }
                    listOfTasks.add(deadline);
                    break;
                default:
                    throw new DukeException("OOPS! Can't Load task from File");
                }
            }
            return listOfTasks;
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format! Please input date using the format yyyy-MM-dd");
        } catch (IOException e) {
            throw new DukeException("OOPS! Can't Load task from File");
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
            FileWriter fileWriter = new FileWriter(storageFile);
            for (int i = 0; i < tasks.getTaskCount(); i++) {
                Task currTask = tasks.getTask(i);
                fileWriter.write(currTask.toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("OOPS! Failed to write task to file.");
        }
    }
}

