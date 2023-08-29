import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                String taskType = values[0].trim();
                boolean isDone = values[1].trim().equals("1");
                String description = values[2].trim();

                if (taskType.equals("T")) {
                    tasks.add(new ToDo(description, isDone));
                } else if (taskType.equals("D")) {
                    String by = values[3].trim();
                    tasks.add(new Deadline(description, isDone, by));
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
