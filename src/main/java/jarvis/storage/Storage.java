package jarvis.storage;

import jarvis.tasklist.TaskList;
import jarvis.task.Deadline;
import jarvis.task.Event;
import jarvis.task.Task;
import jarvis.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private static LocalDateTime parseSavedDateTime(String dateTimeString){
        return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasks(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(this.filePath);

            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).toSaveString() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
            e.printStackTrace();
        }
    }

    public ArrayList<Task> loadTasks() {
        File file = new File(this.filePath);

        if (!file.exists()) {
            System.out.println("No save file detected. Attempting to create one...");
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();  // This creates the directory structure if it doesn't exist
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred while creating a new save file.");
                e.printStackTrace();
            }
            System.out.println("Save file created successfully at " + this.filePath);
        }

        List<String> lines;

        try {
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks.");
            return null;
        }

        ArrayList<Task> tasks = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            boolean isMarked = parts[1].equals("1");

            switch (parts[0]) {
            case "T":
                tasks.add(new Todo(parts[2], isMarked));
                break;
            case "D":
                tasks.add(new Deadline(parts[2], isMarked, parseSavedDateTime(parts[3])));
                break;
            case "E":
                tasks.add(new Event(parts[2], isMarked, parseSavedDateTime(parts[3]), parseSavedDateTime(parts[4])));
                break;
            }
        }
        return tasks; // Return the loaded list of tasks.
    }

    // ... Other storage-related methods ...
}