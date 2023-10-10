package duke;

import java.io.*;
import java.util.ArrayList;

/**
 * Represents a class that handles the loading and saving of tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file for loading and saving tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     *
     * @return An ArrayList containing the loaded tasks.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                String taskType = values[0].trim();
                boolean isDone = values[1].trim().equals("true");
                String description = values[2].trim();

                if (taskType.equals("T")) {
                    tasks.add(new ToDo(description, isDone));
                } else if (taskType.equals("D")) {
                    String byDateAndTime = values[3];
                    System.out.println(byDateAndTime);
                    tasks.add(new Deadline(description, isDone, byDateAndTime));
                } else if (taskType.equals("E")) {
                    String from = values[3].trim();
                    String to = values[4].trim();
                    tasks.add(new Event(description, isDone, from, to));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks An ArrayList containing tasks to be saved.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                String line = task.toSaveString();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
