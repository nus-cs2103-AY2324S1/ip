package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a storage for the chatbot
 */
public class Storage {
    private final String path;
    private final DateTimeFormatter outputFormatter;

    /**
     * A constructor for the storage
     *
     * @param filePath the path to the file
     */
    public Storage(String filePath) {
        this.path = filePath;
        this.outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        // Create file if file does not exist
        try {
            File file = new File(filePath);
            if (!file.createNewFile()) {
                this.load();
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Save the tasks to the file
     *
     * @param tasks the list of tasks
     * @throws IOException if there is an error writing to the file
     */
    public void saveTasks(List<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(this.path);
        for (Task task : tasks) {
            writer.write(task.toFileFormat() + "\n");
        }
        writer.close();
    }

    /**
     * Load the tasks from the file
     *
     * @return the list of tasks
     * @throws IOException if there is an error reading from the file
     */
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(this.path));

        String nextTask = reader.readLine();
        while (nextTask != null) {
            String[] taskDescription = nextTask.split("\\|");
            String isDone = taskDescription[1].strip();
            String description = taskDescription[2].strip();
            switch (taskDescription[0].strip()) {
            case "T":
                tasks.add(new Todo(description));
                break;
            case "D":
                tasks.add(new Deadline(description,
                        LocalDateTime.parse(taskDescription[3].strip(), this.outputFormatter)));
                break;
            case "E":
                tasks.add(new Event(description, LocalDateTime.parse(taskDescription[3].strip(),
                        this.outputFormatter), LocalDateTime.parse(taskDescription[4].strip(), this.outputFormatter)));
                break;
            default:
                break;
            }
            if (isDone.equals("true")) {
                int index = tasks.size() - 1;
                tasks.get(index).markAsDone();
            }
            nextTask = reader.readLine();
        }
        reader.close();
        return tasks;
    }
}
