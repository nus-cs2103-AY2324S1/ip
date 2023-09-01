package duke;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the file used to store the todo list
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the TaskList data to the storage file.
     *
     * @param tasks the ArrayList of tasks to be saved
     */
    public void writeToFile(ArrayList<Task> tasks) {
        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                File parentDir = file.getParentFile();
                if (parentDir != null) {
                    parentDir.mkdirs();
                }
                file.createNewFile();
            }
            StringBuilder result = new StringBuilder();
            for (Task task : tasks) {
                String str = String.format("%s | %s | %s\n", task.getType(),
                        task.isDone() ? 1 : 0, task.getDetails());
                result.append(str);
            }
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(result.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong when saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads the todo list data from this storage file, and then returns it
     * Returns an empty ArrayList of tasks if the file does not exist
     *
     * @throws DukeException when data of invalid format is read from Storage file
     */
    public ArrayList<Task> readFile() {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;
            }
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split("\\s*\\|\\s*");
                String taskType = line[0];
                boolean isDone = line[1].equals("1");
                String description = line[2];
                Task task;
                switch (taskType) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    LocalDateTime by = LocalDateTime.parse(line[3].trim(), inputFormatter);
                    task = new Deadline(description, by);
                    break;
                case "E":
                    LocalDateTime from = LocalDateTime.parse(line[3].trim(), inputFormatter);
                    LocalDateTime to = LocalDateTime.parse(line[4].trim(), inputFormatter);
                    task = new Event(description, from, to);
                    break;
                default:
                    throw new DukeException("Invalid format found in ./data/tasks.txt,"
                            + " please ensure data is in correct format");
                }
                if (isDone) {
                    task.setDone(true);
                }
                tasks.add(task);
            }
        } catch (IOException | DukeException | DateTimeParseException e) {
            System.out.println("Something went wrong when loading tasks: " + e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return tasks;
    }
}
