package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

/**
 * Handles saving and loading of data.
 */
public class DataStorage {

    public ArrayList<Task> taskData;

    /**
     * Initialises a new DataStorage object.
     */
    public DataStorage() {
        taskData = new ArrayList<>();
    }

    /**
     * Loads data from a txt file (duke.txt) into taskList..
     */
    public void loadTasks() {
        try {
            File dataFileDirectory = new File("./data");
            if (!dataFileDirectory.exists()) {
                dataFileDirectory.mkdirs();
            }

            File file = new File(dataFileDirectory, "duke.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                readTasks(line);
            }
            scanner.close();

        } catch (IOException e) {
            System.out.println("Error!" + e.getMessage());
        }
    }

    /**
     * Saves data from taskList onto txt file (duke.txt).
     * @param taskList List of tasks.
     */
    public void saveTasks(ArrayList<Task> taskList) {
        File dataFileDirectory = new File("./data");
        if (!dataFileDirectory.exists()) {
            dataFileDirectory.mkdirs();
        }

        File storageFile = new File(dataFileDirectory, "duke.txt");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(storageFile))) {
            for (Task task : taskList) {
                bufferedWriter.write(task.storageFormat());
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error!" + e.getMessage());
        }
    }

    /**
     * Interprets text as Tasks.
     *
     * @param line String representing a task.
     * @throws IOException If error occurs during execution.
     */
    public void readTasks(String line) throws IOException {
        String[] split = line.split(" \\| ");
        String taskType = split[0];
        boolean isDone = split[1].equals("1");

        Task task;

        if (taskType.equals("T")) {
            task = new ToDo(split[2]);
        } else if (taskType.equals("D")) {
            task = new Deadline(split[2], LocalDateTime.parse(split[3]));
        } else if (taskType.equals("E")) {
            task = new Event(split[2], LocalDateTime.parse(split[3]), LocalDateTime.parse(split[4]));
        } else {
            throw new IOException();
        }

        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsUndone();
        }

        taskData.add(task);
    }

}
