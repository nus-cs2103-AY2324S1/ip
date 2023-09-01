import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class TaskArray extends ArrayList<Task> {
    private String filePath;

    public TaskArray(String filePath) {
        this.filePath = filePath;
        // Load tasks from file in the constructor
        loadTasksFromFile();
    }

    public void addTask(Task task) {
        this.add(task);
        // Save tasks to file whenever the list changes
        saveTasksToFile();
    }

    public void deleteTask(int index) {
        this.remove(index);
        // Save tasks to file whenever the list changes
        saveTasksToFile();
    }

    public void modifyTask(int index, Task modifiedTask) {
        set(index, modifiedTask);
        // Save tasks to file whenever the list changes
        saveTasksToFile();
    }

    public void displayTasks() {
        // Display the list of tasks
        for (Task task : this) {
            System.out.println(task);
        }
    }

    private void loadTasksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Create Task from each line and add them to the list
                String[] parts = line.split(",");
                boolean isMarked = Objects.equals(parts[2], "true");

                if (Objects.equals(parts[0], "Todo")) {
                    this.add(new ToDo(parts[1], isMarked));
                } else if (Objects.equals(parts[0], "Deadline")) {
                    this.add(new Deadline(parts[1], isMarked, parts[3]));
                } else if (Objects.equals(parts[0], "Event")) {
                    this.add(new Event(parts[1], isMarked, parts[3], parts[4]));
                } else {
                    throw new DukeCorruptedDataException("Error: task is invalid. Failed to load data from file.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks from file: " + e.getMessage());
        } catch (DukeCorruptedDataException e) {
            System.err.println(e);
        }
    }

    private void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath))) {
            for (Task task : this) {
                // Format and write each task to the file
                writer.write(task.getTaskDetails());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
