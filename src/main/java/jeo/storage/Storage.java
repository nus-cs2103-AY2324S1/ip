package jeo.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import jeo.exception.JeoException;
import jeo.task.Deadline;
import jeo.task.Event;
import jeo.task.Task;
import jeo.task.TaskList;
import jeo.task.ToDo;

/**
 * Stores data of the list of tasks.
 *
 * @author Joseph Oliver Lim
 */
public class Storage {
    private String directoryString = "./data";
    private String filePathString = directoryString + "/jeo.txt";
    private File file;

    /**
     * Creates a new file to store data if no file exists yet.
     *
     * @throws JeoException If a file cannot be created.
     */
    public void initialize() throws JeoException {
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
            throw new JeoException("OOPS!!! Failed to create file.");
        }
    }

    /**
     * Process a ToDo task from a task description.
     *
     * @param taskDescription A String representing the task description.
     * @return A ToDo Task
     */
    private Task processToDo(String taskDescription) {
        return new ToDo(taskDescription);
    }

    /**
     * Process a Deadline task from a task description.
     *
     * @param taskDescription A String representing the task description.
     * @return A Deadline Task
     */
    private Task processDeadline(String taskDescription) {
        String[] split = taskDescription.split(" \\(by: |\\)");
        return new Deadline(split[0], split[1]);
    }

    /**
     * Process a Event task from a task description.
     *
     * @param taskDescription A String representing the task description.
     * @return A Event Task
     */
    private Task processEvent(String taskDescription) {
        String[] split = taskDescription.split(" \\(from: | to: |\\)");
        return new Event(split[0], split[1], split[2]);
    }

    /**
     * Process a line in the input file.
     *
     * @param input A String representing a task.
     * @return A Task that is read from the line.
     * @throws JeoException If the input line is of invalid format.
     */
    private Task processLine(String input) throws JeoException {
        String taskType = input.substring(1, 2);
        String taskMark = input.substring(4, 5);
        String taskDescription = input.substring(7);
        Task task;
        switch (taskType) {
        case "T":
            task = processToDo(taskDescription);
            break;
        case "D":
            task = processDeadline(taskDescription);
            break;
        case "E":
            task = processEvent(taskDescription);
            break;
        default:
            throw new JeoException("OOPS!!! Failed to load tasks from file.");
        }
        if (taskMark.equals("X")) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Reads from the file to retrieve the list of tasks.
     *
     * @return A TaskList containing tasks read from the file.
     * @throws JeoException If the reading file process failed.
     */
    public TaskList readFile() throws JeoException {
        try {
            TaskList tasks = new TaskList();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                tasks.addTask(processLine(input));
            }
            return tasks;
        } catch (DateTimeParseException e) {
            throw new JeoException("OOPS!!! Invalid date format. Please type dates in the format yyyy-mm-dd");
        } catch (IOException e) {
            throw new JeoException("OOPS!!! Failed to load tasks from file.");
        }
    }

    /**
     * Writes the list of tasks to the file.
     *
     * @param tasks The tasks to be written to the file.
     * @throws JeoException If the writing to file process failed.
     */
    public void writeToFile(TaskList tasks) throws JeoException {
        try {
            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < tasks.getCountTasks(); i++) {
                Task task = tasks.getTask(i);
                fw.write(task.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new JeoException("OOPS!!! Failed to write to file.");
        }
    }
}
