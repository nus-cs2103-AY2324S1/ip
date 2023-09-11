package main.java.storage;

import main.java.Deadline;
import main.java.Event;
import main.java.Task;
import main.java.ToDo;
import main.java.tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * The Storage class handles data storage and retrieval for tasks using files.
 */
public class Storage {
    private final Path directory;
    private File file;

    /**
     * Constructs a new Storage instance with the specified file path.
     * Initializes the storage directory and creates the file if it doesn't exist.
     *
     * @param filePath The file path within the storage directory.
     */
    public Storage(String filePath) {
        this.directory = Paths.get(System.getProperty("user.dir"), "./ip/src/main/java/storage");
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        this.file = new File(this.directory.toFile(), filePath);
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Saves the task list's data to the storage file.
     *
     * @param taskList The task list containing tasks to be saved.
     */
    public void saveTaskList(TaskList taskList) {
        try (FileWriter fileWriter = new FileWriter(this.file)) {
            for (int i = 0; i < taskList.numOfTasks(); i++) {
                Task taskToSave = taskList.getTask(i);
                String toSave = taskToSave.getSaveDescription();
                fileWriter.write(toSave);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads task data from the storage file into the task list.
     *
     * @param taskList The task list to populate with loaded tasks.
     */
    public void loadTaskList(TaskList taskList) {
        try (Scanner sc = new Scanner(this.file)) {
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                String[] task = input.split(" \\| ", 5);
                Boolean taskBool = task[1] == "1";
                String description = task[2];
                switch (task[0]) {
                case ("T"):
                    taskList.addTask(new ToDo(description, taskBool));
                    break;
                case ("D"):
                    taskList.addTask(new Deadline(description, task[3], taskBool));
                    break;
                case ("E"):
                    taskList.addTask(new Event(description, task[3], task[4], taskBool));
                    break;
                default:
                    System.out.println("unknown file format");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
