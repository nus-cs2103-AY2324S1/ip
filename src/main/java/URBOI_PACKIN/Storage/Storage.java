package URBOI_PACKIN.Storage;

import URBOI_PACKIN.Task;
import URBOI_PACKIN.TaskTypes.Deadline;
import URBOI_PACKIN.TaskTypes.Event;
import URBOI_PACKIN.TaskTypes.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "tasks.txt";

    public static void loadTasksFromFile(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    Task task = createTaskFromLine(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
                fileScanner.close();
            }
        } catch (FileNotFoundException e) {
            // Handle file not found exception
            System.out.println("File not found: " + FILE_PATH);
        }
    }

    public static void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                fileWriter.write(task.toFileString() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            // Handle IO exception
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private static Task createTaskFromLine(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }

        String type = parts[0];
        String status = parts[1];
        String description = parts[2];

        Task task = null;
        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                if (parts.length >= 4) {
                    String by = parts[3];
                    task = new Deadline(description, LocalDateTime.parse(by, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                }
                break;
            case "E":
                if (parts.length >= 5) {
                    String from = parts[3];
                    String to = parts[4];
                    task = new Event(description, from, to);
                }
                break;
        }

        if (task != null) {
            if (status.equals("1")) {
                task.markDone();
            } else {
                task.markNotDone();
            }
        }

        return task;
    }
}
