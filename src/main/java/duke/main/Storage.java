package duke.main;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Storage class handles loading and saving tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param filePath The path to the file used for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file in the constructor.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws IOException If there's an error while reading the file.
     */
    public ArrayList<Task> load() throws IOException {
        File f = new File(this.filePath);
        if (!f.exists()) f.createNewFile();

        Scanner sc = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            Task task;
            String[] taskDetails = sc.nextLine().split(" , ");
            switch (taskDetails[0]) {
                case "T":
                    task = new Todo(taskDetails[2], taskDetails[1].equals("1"));
                    break;
                case "D":
                    task = new Deadline(taskDetails[2], taskDetails[3], taskDetails[1].equals("1"));
                    break;
                case "E":
                    task = new Event(taskDetails[2], taskDetails[3], taskDetails[4], taskDetails[1].equals("1"));
                    break;
                default:
                    task = null;
                    break;
            }
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Appends a task to the file specified in the constructor.
     *
     * @param task The task to be appended to the file.
     * @throws IOException If there's an error while writing to the file.
     */
    public void append(Task task) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true);
        fw.write(task.getTaskFileString() + System.lineSeparator());
        fw.close();
    }

    /**
     * Update the file with a list of tasks, for when deleting or marking.
     *
     * @param tasks The list of tasks to be saved to the file.
     * @throws IOException If there's an error while writing to the file.
     */
    public void update(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        tasks.forEach(task -> {
            try {
                fw.write(task.getTaskFileString() + System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        fw.close();
    }
}
