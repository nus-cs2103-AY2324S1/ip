package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The Storage class handles loading and saving tasks to a file.
 */
public class Storage {

    private String filePath;

    /**
     * Constructs a new Storage object with the specified file path.
     *
     * @param filePath The file path where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     *
     * @return A list of Task objects loaded from the file.
     * @throws DukeException If there is an issue or error encountered during loading.
     */
    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        try {
            String directoryPath = file.getParent();
            File directory = new File(directoryPath);

            if (!directory.exists()) {
                directory.mkdirs();
                System.out.println("Directory created: " + directoryPath);
            }

            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created: " + filePath);
            }
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                String taskType = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String description = parts[2].trim();

                if (taskType.equals("T")) {
                    tasks.add(new ToDo(description, isDone));
                } else if (taskType.equals("D")) {
                    LocalDate by = LocalDate.parse(parts[3].trim());
                    tasks.add(new Deadline(description, by, isDone));
                } else if (taskType.equals("E")) {
                    String from = parts[3].trim();
                    String to = parts[4].trim();
                    tasks.add(new Event(description, from, to, isDone));
                } else {
                    throw new DukeException("☹ OOPS!!! Invalid task type in file");
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! Error loading tasks from the file");
        }
    }

    /**
     * Saves the tasks from the TaskList to the file specified by the file path.
     *
     * @param tasks The TaskList object containing the tasks to be saved.
     * @throws DukeException If there is an issue or error encountered during saving.
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks.getAll()) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! Error saving tasks to the file");
        }
    }
}