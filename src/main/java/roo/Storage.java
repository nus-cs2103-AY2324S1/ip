package roo;

import roo.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private File file;
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage (String filePath) {
        this.filePath = filePath;
        file = new File(this.filePath);
    }

    /**
     * Initializes the Storage and reads task data from the file if it exists.
     * @param tasks The TaskList to populate with read tasks.
     */
    public void initialise(TaskList tasks) {
        try {
            this.file = new File(this.filePath);
            if (!file.createNewFile()) {
                this.readFileContents(tasks);
            }
        } catch (IOException e) {
            System.err.println("I/O error greet: " + e.getMessage());
        }
    }

    /**
     * Reads the contents of the file and adds tasks to the given TaskList.
     * @param tasks The TaskList to add the tasks to.
     * @throws FileNotFoundException If the file is not found.
     */
    private void readFileContents(TaskList tasks) throws FileNotFoundException {
        Scanner sc = new Scanner(this.file);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (!input.isEmpty()) {
                tasks.add(Parse.makeTask(input));
            }
        }
        sc.close();
    }

    /**
     * Clears the file contents by deleting and recreating the file.
     */
    public void clear() {
        try {
            Files.delete(Paths.get(filePath));
            this.file = new File(this.filePath);
        } catch (IOException err) {
            System.err.println("Error clearing " + err.getMessage());
        }
    }

    /**
     * Modifies the file contents to match the provided list of tasks.
     * @param tasks The list of tasks to update the file with.
     */
    public void modifyFile(ArrayList<Task> tasks) {
        try {
            Files.delete(Paths.get(filePath));
            this.file = new File(this.filePath);
            FileWriter writer = new FileWriter(filePath, true);
            for (Task t : tasks) {
                writer.write(t.toString()  + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("I/O error modify " + e.getMessage());
        }
    }

    /**
     * Adds a task to the file.
     * @param task The task to be added to the file.
     */
    public void add(Task task) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(task.toString() + "\n");
            writer.close();
        } catch (IOException e) {
            System.err.println("I/O error add " + e.getMessage());
        }

    }
}
