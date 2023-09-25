package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * Manages the saving and loading of tasks to/from duke.txt.
 */
public class Save {
    private static final String DIRECTORY_PATH = "./data";
    private static final String FILE_PATH = DIRECTORY_PATH + "/duke.txt";
    private static final String ARCHIVE_FILE_PATH = DIRECTORY_PATH + "/archive.txt";

    /**
     * Saves the list of tasks to a duke.txt.
     *
     * @param tasks List of tasks to be saved.
     */
    public void saveTasks(ArrayList<Task> tasks, String destination) throws DukeException {
        assert tasks != null;
        try {
            File directory = new File(DIRECTORY_PATH);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination, true))) {
                for (Task task : tasks) {
                    writer.write(task.format());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new DukeException("Well, the bad news is that the Tasks could not be saved: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file and returns them as a list.
     * If no saved tasks are found, an empty list is returned.
     *
     * @return List of loaded tasks.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return tasks;
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    Task task = parseLineToTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                } catch (DukeException ohno) {
                    System.out.println("Ok, so I cannot parse this: " + ohno.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Hey, I cannot read this: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the list of tasks to a archive.txt.
     *
     * @param tasks List of tasks to be saved.
     */
    public void archiveTasks(ArrayList<Task> tasks) throws DukeException {
        saveTasks(tasks, ARCHIVE_FILE_PATH);
    }

    public void saveToDefault(ArrayList<Task> tasks) throws DukeException {
        saveTasks(tasks, FILE_PATH);
    }

    /**
     * Parses a single line from the file to recreate the respective Task object.
     * Recognizes the format of Todos, Deadlines, and Events.
     *
     * @param line The line from the file.
     * @return The parsed Task object or null if the line format is unrecognized.
     */
    private static Task parseLineToTask(String line) throws DukeException {
        assert line != null;
        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
        case "T":
            Todo todo = new Todo(parts[2]);
            if (parts[1].equals("1")) {
                todo.markAsDone();
            }
            return todo;
        case "D":
            if (parts.length < 4) {
                throw new DukeException(" Looks like this Deadline is corrupted: " + line);
            }
            Deadline deadline = new Deadline(parts[2], parts[3]);
            if (parts[1].equals("1")) {
                deadline.markAsDone();
            }
            return deadline;
        case "E":
            if (parts.length < 4) {
                throw new DukeException(" Looks like this Event is corrupted: " + line);
            }
            String[] timeParts = parts[3].split(" - ");
            if (timeParts.length < 2) {
                throw new DukeException(" Looks like this Event's timing is corrupted: " + line);
            }
            Event event = new Event(parts[2], timeParts[0].trim(), timeParts[1].trim());
            if (parts[1].equals("1")) {
                event.markAsDone();
            }
            return event;
        default:
            throw new DukeException(" Yea mate no clue what task this is: " + line);
        }
    }
}
