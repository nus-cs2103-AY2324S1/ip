package simon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import simon.task.Deadline;
import simon.task.Event;
import simon.task.Task;
import simon.task.ToDo;


/**
 * The {@code Storage} class handles the loading and saving of tasks to a specified file.
 * It ensures that tasks are persisted across multiple runs of the Simon application.
 */
public class Storage {

    /** The path to the file where tasks are stored. */
    private final String filePath;

    /**
     * Constructs a new {@code Storage} instance with the specified file path.
     *
     * @param filePath The path to the file used for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file. If the file does not exist, it is created.
     *
     * @return An {@code ArrayList} of tasks loaded from the file.
     * @throws SimonException If there is an error parsing the saved data.
     */
    public ArrayList<Task> load() throws SimonException {
        ArrayList<Task> loadedTasks = new ArrayList<>();

        // Ensure data directory exists
        File dir = new File("./data");
        if (!dir.exists()) {
            dir.mkdir();
        }

        // Ensure simon.txt file exists
        File file = new File(this.filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred while creating the data file.");
                e.printStackTrace();
            }
        }

        try {
            Scanner scanner = new Scanner(file);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] parts = data.split(" \\| ");
                switch (parts[0]) {
                    case "T":
                        ToDo todo = new ToDo(parts[2]);
                        if (parts[1].equals("1")) {
                            todo.markAsDone();
                        }
                        loadedTasks.add(todo);
                        break;
                    case "D":
                        LocalDateTime endDateTime = LocalDateTime.parse(parts[3], formatter);
                        Deadline deadline = new Deadline(parts[2], endDateTime.format(formatter));
                        if (parts[1].equals("1")) {
                            deadline.markAsDone();
                        }
                        loadedTasks.add(deadline);
                        break;
                    case "E":
                        String startDateTimeStr = parts[3];
                        String endDateTimeStr = parts[4];
                        LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeStr, formatter);
                        LocalDateTime endDateTime2 = LocalDateTime.parse(endDateTimeStr, formatter);
                        Event event = new Event(parts[2], startDateTime.format(formatter), endDateTime2.format(formatter));
                        if (parts[1].equals("1")) {
                            event.markAsDone();
                        }
                        loadedTasks.add(event);
                        break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Starting with an empty task list.");
        } catch (SimonException e) {
            throw new RuntimeException(e);
        }

        return loadedTasks;
    }

    /**
     * Saves the provided list of tasks to the specified file.
     *
     * @param tasks The {@code ArrayList} of tasks to be saved.
     */
    public void save(ArrayList<Task> tasks) {
        try {
            PrintWriter writer = new PrintWriter(this.filePath);
            for (Task task : tasks) {
                if (task instanceof ToDo) {
                    writer.println("T | " + (task.isDone ? "1" : "0") + " | " + task.taskName);
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    writer.println("D | " + (task.isDone ? "1" : "0") + " | " + task.taskName + " | " + deadline.endDateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    writer.println("E | " + (task.isDone ? "1" : "0") + " | " + task.taskName + " | " + event.startDateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")) + " | " + event.endDateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
                }
            }
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
