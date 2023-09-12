package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Handles data storage and retrieval.
 */
public class Storage {
    /** The file where tasks are stored. */
    private File file;

    /**
     * Constructs a Storage object and creates the necessary directories and files.
     *
     * @param filePath The path to the file for data storage.
     */
    public Storage(String filePath) {
        try {
            String[] splited = filePath.split("/");
            File dir = new File(splited[0]);

            if (!dir.exists()) {
                dir.mkdir();
            }
            File content = new File(filePath);
            if (!content.exists()) {
                content.createNewFile();
           }
            this.file = content;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Retrieves data from the storage file and converts it into an Array list of tasks.
     *
     * @return An ArrayList of Task objects loaded from the storage file.
     * @throws DukeException If there are any issues during data retrieval.
     */
    public ArrayList<Task> getData() throws DukeException {
        try {
            ArrayList<Task> oldTasks = new ArrayList<>();
            Scanner fileScanner = new Scanner(this.file);

            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] splited = line.split(" ", 6);
                String taskType = splited[2];
                switch (taskType) {
                    case "[T]" :
                        readTodo(splited[5], splited[0], oldTasks);
                    case "[D]" :
                        readDeadline(splited[5], splited[0], oldTasks);
                    case "[E]" :
                        readEvent(splited[5], splited[0], oldTasks);

                }
            }
            return oldTasks;

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void readTodo(String description, String status, ArrayList<Task> oldTasks) {
        Task todo = new Todo(description);
        oldTasks.add(todo);
        if (status.equals("1")) {
            todo.markDone();
        } else {
            todo.markUndone();
        }
    }

    public void readDeadline(String description, String status, ArrayList<Task> oldTasks) {
        String[] desc = description.split("\\(by:", 2);
        String time = desc[1].split("\\)", 2)[0];
        LocalDateTime by = formatData(time);
        Task deadline = new Deadline(desc[0], by);
        oldTasks.add(deadline);
        if (status.equals("1")) {
            deadline.markDone();
        } else {
            deadline.markUndone();
        }
    }

    public void readEvent(String description, String status, ArrayList<Task> oldTasks) {
        String[] desc = description.split("\\(from:");
        String start = desc[1].split("to:", 2)[0];
        String end = desc[1].split("to:", 2)[1];
        LocalDateTime from = formatData(start);
        LocalDateTime to = formatData(end);
        Task event = new Event(desc[0], from, to);
        oldTasks.add(event);
        if (status.equals("1")) {
            event.markDone();
        } else {
            event.markUndone();
        }
    }

    /**
     * Saves the list of tasks into the storage txt file.
     *
     * @param tasks The list of Task objects to be saved.
     */
    public void saveData(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(this.file);
            for (Task task : tasks) {
                int isDone = task.getStatus() ? 1 : 0;
                String description = task.toString();
                String line = String.format("%d | %s%n", isDone, description);
                writer.write(line);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Converts a formatted date and time string into a LocalDateTime object.
     *
     * @param data The formatted date and time string.
     * @return A LocalDateTime object parsed from the input string.
     */
    public LocalDateTime formatData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'of' MMMM uuuu, h:mma", Locale.ENGLISH);
        LocalDateTime localDateTime = LocalDateTime.parse(data, formatter);
        return localDateTime;
    }
}
