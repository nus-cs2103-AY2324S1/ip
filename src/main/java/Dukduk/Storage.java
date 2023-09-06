package dukduk;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    public static ArrayList<Task> loadTasksFromFile(String filePath) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                System.out.println("No existing tasks file found. Starting with an empty task list.");
                return tasks;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    Task task = Task.createTaskFromDataString(line);
                    tasks.add(task);
                    String[] parts = line.split(" \\| ");
                    if (parts.length >= 2 && parts[1].equals("1")) {
                        task.markAsDone();
                    }
                } catch (DukdukException e) {
                    System.out.println("Error parsing task data: " + e.getMessage());
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    public static void saveTasksToFile(String filePath, ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toDataString());
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
