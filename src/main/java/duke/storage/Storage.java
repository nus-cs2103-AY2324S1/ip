package duke.storage;

import duke.DukeException;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDoTask;
import duke.ui.Ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The Storage class handles the storage and retrieval of task data for the Duke application.
 * It handles the creation of necessary directories and files, as well as loading and saving tasks to/from a file.
 */
public class Storage {

    /** The file path for the storage file. */
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path and ensures the file and directory structure exists.
     *
     * @param filePath The file path for storing task data.
     * @throws DukeException If an error occurs while creating directories or files.
     */
    public Storage(String filePath) throws DukeException {
        this.filePath = filePath;

        try {
            accessOrCreateFile(filePath);
        }  catch (DukeException e) {
            throw e;
        }
    }

    /**
     * Ensures the directory structure and file exist or creates them if necessary.
     *
     * @param filePath The file path to check or create.
     * @throws DukeException If an error occurs while creating directories or files.
     */
    private void accessOrCreateFile(String filePath) throws DukeException {
        try {
            File file = new File(filePath);
            File directory = file.getParentFile();

            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    System.out.println("Directory created: " + directory.getAbsolutePath());
                } else {
                    System.out.println("Failed to create directory.");
                }
            }

            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getAbsolutePath());
                } else {
                    System.out.println("Failed to create file.");
                }
            }
        } catch (IOException e) {
            throw new DukeException("Error creating file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file and returns them as an ArrayList of tasks.
     *
     * @return An ArrayList of tasks loaded from the storage file.
     * @throws DukeException If an error occurs while reading from the file.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks;
        try {
            tasks = loadTasksFromFile();
            return tasks;
        } catch (IOException e) {
            throw new DukeException("Error loading duke.tasks from file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file and parses them into Task objects, then returns them as an ArrayList.
     *
     * @return An ArrayList of Task objects loaded from the storage file.
     * @throws IOException If an error occurs while reading from the file or if the file contains invalid data.
     */
    private ArrayList<Task> loadTasksFromFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" \\| ");
            String taskType = parts[0];
            boolean isDone = parts[1].equals("X");
            String taskDescription = parts[2];

            Task task;

            if (taskType.equals("T")) {
                task = new ToDoTask(taskDescription);
            } else if (taskType.equals("D")) {
                LocalDateTime deadline = LocalDateTime.parse(parts[3], Ui.DATE_FORMAT_OUTPUT);
                task = new DeadlineTask(taskDescription, deadline);
            } else if (taskType.equals("E")) {
                LocalDateTime from = LocalDateTime.parse(parts[3], Ui.DATE_FORMAT_OUTPUT);
                LocalDateTime to = LocalDateTime.parse(parts[4], Ui.DATE_FORMAT_OUTPUT);
                task = new EventTask(taskDescription, from, to);
            } else {
                throw new IOException("Invalid task type found in file. Data file may be corrupted.");
            }

            if (isDone) {
                task.markAsDone();
            }
            tasks.add(task);
        }

        reader.close();
        return tasks;
    }

    /**
     * Saves tasks to the storage file.
     *
     * @param taskList The TaskList containing tasks to be saved.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void saveTasksToFile(TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        ArrayList<Task> tasks = taskList.getTasks();
        for (Task task : tasks) {
            fileWriter.write(task.toFileString() + "\n");
        }

        fileWriter.close();
    }
}
