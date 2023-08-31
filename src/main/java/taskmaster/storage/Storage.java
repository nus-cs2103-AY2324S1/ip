package taskmaster.storage;

import taskmaster.tasks.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    /**
     * File path that stores saved tasks
     */
    private static String filePath;
    /**
     * Constructor for the Storage class.
     *
     * @param filePath Relative path of the file that stores the data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Loads the stored data into the current task list.
     */
    public void loadTasksFromFile() {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
                char type = line.charAt(1);
                char mark = line.charAt(4);

                if (type == 'T') {
                    String description = line.substring(6).trim();
                    if (mark == 'X') {
                        TaskList.list.add(new Todo(description, "marked" ));
                    } else {
                        TaskList.list.add(new Todo(description, "unmarked"));
                    }
                } else if (type == 'D') {
                    int byIndex = line.indexOf("(by: ");
                    String description = line.substring(7, byIndex).trim(); // 7 is the length of "[D][ ] "
                    String dueDate = line.substring(byIndex + 5, line.length() - 1).trim();

                    if (mark == 'X') {
                        TaskList.list.add(new Deadline(description, dueDate, "marked"));
                    } else {
                        TaskList.list.add(new Deadline(description, dueDate, "unmarked"));
                    }
                } else if (type == 'E') {
                    String[] parts = line.split("\\(from: | to ");
                    String description = parts[0].trim().substring(7);
                    String startTime = parts[1].trim();
                    String endTime = parts[2].replace(")", "").trim();

                    if (mark == 'X') {
                        TaskList.list.add(new Event(description, startTime, endTime, "marked"));
                    } else {
                        TaskList.list.add(new Event(description, startTime, endTime, "unmarked"));
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found: " + e.getMessage());
        }
    }

    /**
     * Saves the task list to the file
     */
    public static void saveTasksToFile() {
        try {
            FileWriter writer = new FileWriter(filePath);

            for (Task task : TaskList.list) {
                writer.write(task.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

}

