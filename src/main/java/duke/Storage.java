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
 * Storage handles reading and writing tasks to a file for persistence.
 * It interacts with the DukeList class to save and load tasks.
 */
public class Storage {
    /** The file where tasks are stored. */
    private File file;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The file path to read and store tasks.
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
     * Reads tasks from the file and returns them as an ArrayList of Task objects.
     *
     * @return An ArrayList containing tasks read from the file.
     * @throws DukeException If an error occurs while reading the file.
     */
    public ArrayList<Task> getData() throws DukeException {
        try {
            ArrayList<Task> oldTasks = new ArrayList<>();
            Scanner fileScanner = new Scanner(this.file);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] splited = line.split(" ", 6);
                if (splited[2].equals("[T]")) {
                    Task todo = new Todo(splited[5]);
                    oldTasks.add(todo);
                    if (splited[0].equals("1")) {
                        todo.markDone();
                    } else {
                        todo.markUndone();
                    }
                }
                if (splited[2].equals("[D]")) {
                    String[] desc = splited[5].split("\\(by:", 2);
                    String time = desc[1].split("\\)", 2)[0];
                    LocalDateTime by = formatData(time);
                    Task deadline = new Deadline(desc[0], by);
                    oldTasks.add(deadline);
                    if (splited[0].equals("1")) {
                        deadline.markDone();
                    } else {
                        deadline.markUndone();
                    }
                }
                if (splited[2].equals("[E]")) {
                    String[] description = splited[5].split("\\(from:");
                    String start = description[1].split("to:", 2)[0];
                    String end = description[1].split("to:", 2)[1];
                    LocalDateTime from = formatData(start);
                    LocalDateTime to = formatData(end);
                    Task event = new Event(description[0], from, to);
                    oldTasks.add(event);
                    if (splited[0].equals("1")) {
                        event.markDone();
                    } else {
                        event.markUndone();
                    }
                }
            }
            return oldTasks;

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks The ArrayList of Task objects to be saved to the file.
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
     * Converts a string to a LocalDateTime object using a specified date and time format.
     *
     * @param data The string containing date and time information to be parsed.
     * @return A LocalDateTime object representing the parsed date and time.
     */
    public LocalDateTime formatData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'of' MMMM uuuu, h:mma", Locale.ENGLISH);
        LocalDateTime localDateTime = LocalDateTime.parse(data, formatter);
        return localDateTime;
    }
}
