package duke;

import duke.task.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Storage {

    /** The file path to store, read and save all tasks within the program. */
    private Path filePath;

    /**
     * Creates a new Storage object with the given filePath.
     *
     * @param filePath The file path to store, read and save all tasks within the program.
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Processes a line of the file retrieved from the hard disk, and adds the corresponding task into a specified
     * TaskList if the line is valid.
     *
     * @param line The line of the file to be parsed and added into the TaskList.
     * @param tasks The TaskList to store any new Task parsed into.
     */
    public void processLine(String line, TaskList tasks) {
        Task newTask;
        String[] lineSeq = line.split(" \\| ");
        try {
            if (line.startsWith("T") && lineSeq.length == 3
                    && (Objects.equals(lineSeq[1], "1") || Objects.equals(lineSeq[1], "0"))) {
                newTask = new Todo(lineSeq[2]);
                if (Objects.equals(lineSeq[1], "1")) newTask.markAsDone();
                tasks.add(newTask);
            } else if (line.startsWith("D") && lineSeq.length == 4
                    && (Objects.equals(lineSeq[1], "1") || Objects.equals(lineSeq[1], "0"))) {
                newTask = new Deadline(lineSeq[2], LocalDateTime.parse(lineSeq[3]));
                if (Objects.equals(lineSeq[1], "1")) newTask.markAsDone();
                tasks.add(newTask);
            } else if (line.startsWith("E") && lineSeq.length == 5
                    && (Objects.equals(lineSeq[1], "1") || Objects.equals(lineSeq[1], "0"))) {
                newTask = new Event(lineSeq[2], LocalDateTime.parse(lineSeq[3]), LocalDateTime.parse(lineSeq[4]));
                if (Objects.equals(lineSeq[1], "1")) newTask.markAsDone();
                tasks.add(newTask);
            } else {
                // else do nothing, specifying that the task is invalid.
                System.out.println("The task " + line + " is invalid and is ignored!");
            }
        } catch (DateTimeParseException e) {
            // do nothing, specifying that the task is invalid.
            System.out.println("The task " + line + " is invalid and is ignored!");
        }
    }

    /**
     * Loads all tasks from the file in the hard disk, and returns the TaskList containing all tasks processed from the
     * file.
     *
     * @return TaskList containing all valid tasks processed from the file; else empty TaskList if any error in reading
     * the file occurs.
     */
    public TaskList load() {
        TaskList tasks = new TaskList();
        boolean fileExists = Files.exists(filePath);
        try {
            if (!fileExists) {
                Files.createFile(filePath);
            } else {
                List<String> contents = Files.readAllLines(filePath);
                for (String line : contents) {
                    processLine(line, tasks);
                }
            }
            return tasks;
        } catch (IOException e) {
            System.err.println("Cannot read initial tasks from file!");
            return new TaskList();
        }
    }

    /**
     * Saves all tasks in the chatbot into the file in the hard disk.
     *
     * @param tasks The list of tasks to be saved into the file.
     */
    public void saveTasks(TaskList tasks) {
        boolean fileExists = Files.exists(filePath);
        try {
            if (!fileExists) {
                Files.createFile(filePath);
            } else {
                ArrayList<String> lines = tasks.getSavedStrings();
                Files.write(filePath, lines);
            }
        } catch (IOException e) {
            System.err.println("Cannot write tasks to file!");
            e.printStackTrace();
        }
    }
}
