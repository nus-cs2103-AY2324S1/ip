package duke;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        ensureFolderExists();
        ensureFileExists();
    }

    private void ensureFolderExists() {
        File folder = new File(filePath).getParentFile();
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    private void ensureFileExists() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }

            if (file.length() == 0) {
                // File is empty, return an empty list
                return tasks;
            }

            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
//                    System.out.println("Line: " + line); // Add this line for debugging
                    String[] parts = line.split(" \\| ");

                    if (parts.length >= 3) { // Ensure there are enough parts to proceed
                        String type = parts[0];
                        String isDone = parts[1];
                        String description = parts[2];

                        switch (type) {
                            case "T":
                                tasks.add(new ToDo(description, isDone));
                                break;
                            case "D":
                                tasks.add(parseDeadline(parts, isDone));
                                break;
                            case "E":
                                tasks.add(parseEvent(parts, isDone));
                                break;
                        }
                    } else {
                        System.out.println("Skipping line with insufficient parts: " + line);
                    }
                }
            }
        } catch (IOException e) {
            // Handle file reading error
            e.printStackTrace();
        }

        return tasks;
    }

    private Deadline parseDeadline(String[] parts, String isDone) {
        String description = parts[2];
        String dueBy = parts[3];
        LocalDate dueDate = LocalDate.parse(dueBy);  // Parse the date
        return new Deadline(description, dueDate, isDone);
    }

    private Event parseEvent(String[] parts, String isDone) {
        String description = parts[2];
        String startTiming = parts[3];
        String endTiming = parts[4];
        LocalDate startTime = LocalDate.parse(startTiming);  // Parse the datetime
        LocalDate endTime = LocalDate.parse(endTiming);  // Parse the datetime
        return new Event(description, startTime, endTime, isDone);
    }

    public void saveTasks(List<Task> tasks) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }

            try (FileWriter writer = new FileWriter(file)) {
                for (Task task : tasks) {
                    String taskType = task instanceof ToDo ? "T" : task instanceof Deadline ? "D" : "E";

                    String taskData = taskType + " | " + task.toDataString() + "\n";
                    writer.write(taskData);
                }
            }
        } catch (IOException e) {
            // Handle file writing error
            e.printStackTrace();
        }
    }
}



