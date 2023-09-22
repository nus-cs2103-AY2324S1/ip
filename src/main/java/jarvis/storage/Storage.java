package jarvis.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import jarvis.task.Deadline;
import jarvis.task.Event;
import jarvis.task.Task;
import jarvis.task.Todo;
import jarvis.tasklist.TaskList;

/**
 * The Storage class is responsible for saving and loading the task list to and from the hard disk.
 */
public class Storage {
    private static final DateTimeFormatter SAVE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");

    private String filePath;

    /**
     * Constructs a Storage object.
     *
     * @param filePath The path to the save file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the task list to the save file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasks(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(this.filePath);

            assert tasks != null : "Tasks list to be saved should not be null";

            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).toSaveString() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
            e.printStackTrace();
        }
    }

    /**
     * Loads the task list from the save file.
     *
     * @return The list of tasks loaded from the save file.
     */
    public ArrayList<Task> loadTasks() {
        File file = new File(this.filePath);

        assert file != null : "File path to load tasks from cannot be null";

        if (!file.exists()) {
            System.out.println("No save file detected. Attempting to create one...");
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs(); // This creates the directory structure if it doesn't exist
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred while creating a new save file.");
                e.printStackTrace();
            }
            System.out.println("Save file created successfully at " + this.filePath);
        }

        try {
            List<String> lines = Files.readAllLines(file.toPath());
            return getTasks(lines);
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks.");
            return null;
        }
    }

    private LocalDateTime parseSavedDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, SAVE_DATE_TIME_FORMATTER);
    }

    private ArrayList<Task> getTasks(List<String> lines) {
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
            default:
                break;
            }
        }
        return tasks;
    }
}
