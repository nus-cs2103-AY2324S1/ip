package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Stores data of the list of tasks.
 *
 * @author Joseph Oliver Lim
 */
public class Storage {
    private String directoryString = "./data";
    private String filePathString = directoryString + "/duke.txt";
    private File file;

    /**
     * Creates a new file to store data if no file exists yet.
     *
     * @throws DukeException If a file cannot be created.
     */
    public void initialize() throws DukeException {
        try {
            File directory = new File(directoryString);
            if (!directory.exists()) {
                directory.mkdir();
            }
            file = new File(filePathString);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Failed to create file.");
        }
    }

    /**
     * Reads from the file to retrieve the list of tasks.
     *
     * @return A TaskList containing tasks read from the file.
     * @throws DukeException If the reading file process failed.
     */
    public TaskList readFile() throws DukeException {
        try {
            TaskList tasks = new TaskList();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                String taskType = input.substring(1, 2);
                String taskMark = input.substring(4, 5);
                String taskDescription = input.substring(7);
                if (taskType.equals("T")) {
                    Task toDo = new ToDo(taskDescription);
                    if (taskMark.equals("X")) {
                        toDo.markAsDone();
                    }
                    tasks.addTask(toDo);
                } else if (taskType.equals("D")) {
                    String[] split = taskDescription.split(" \\(by: |\\)");
                    Task deadline = new Deadline(split[0], split[1]);
                    if (taskMark.equals("X")) {
                        deadline.markAsDone();
                    }
                    tasks.addTask(deadline);
                } else if (taskType.equals("E")) {
                    String[] split = taskDescription.split(" \\(from: | to: |\\)");
                    Task event = new Event(split[0], split[1], split[2]);
                    if (taskMark.equals("X")) {
                        event.markAsDone();
                    }
                    tasks.addTask(event);
                } else {
                    throw new DukeException("OOPS!!! Failed to load tasks from file.");
                }
            }
            return tasks;
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! Invalid date format. Please type dates in the format yyyy-mm-dd");
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Failed to load tasks from file.");
        }
    }

    /**
     * Writes the list of tasks to the file.
     *
     * @param tasks The tasks to be written to the file.
     * @throws DukeException If the writing to file process failed.
     */
    public void writeToFile(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < tasks.getCountTasks(); i++) {
                Task task = tasks.getTask(i);
                fw.write(task.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Failed to write to file.");
        }
    }
}
