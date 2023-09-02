package duck;

import duck.task.Task;
import duck.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles changes to the task history.
 */
public class Storage {
    private String filePath;

    /**
     * Initialises a new Storage object.
     * 
     * @param filePath The path to the file where the tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Rewrites the entire task history.
     * 
     * @param tasks The list of tasks.
     * @throws DuckException If there are problems accessing or writing to the file.
     */
    public void updateTasks(TaskList tasks) throws DuckException {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (int i = 0; i < tasks.getTaskCount(); i++) {
                writer.write(tasks.getTask(i).stringify() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DuckException("Error - unable to save tasks.");
        }
    }

    /**
     * Loads the task history from the file.
     * 
     * @return The list of tasks.
     * @throws DuckException If there are problems accessing, reading or parsing from the file.
     */
    public ArrayList<Task> loadTasks() throws DuckException {
        ArrayList<Task> tasks = new ArrayList<>();

        File dataDir = new File(filePath).getParentFile();
        dataDir.mkdir(); // Create directory only if it doesnt already exist

        File taskFile;
        try {
            taskFile = new File(filePath);
            if (!taskFile.createNewFile()) { // Create file only if it doesnt already exist
                Scanner fileScanner = new Scanner(taskFile);
                while (fileScanner.hasNextLine()) {
                    Task t = Parser.parseFromFile(fileScanner.nextLine()); // should be handled by parser
                    tasks.add(t);
                }
                fileScanner.close();
            }
        } catch (IOException e) {
            throw new DuckException("Error - unable to access task file.");
        }
        return tasks;
    }

    
    /**
     * Appends instead of rewriting the file.
     * 
     * @param newTask The new task to be added.
     * @throws DuckException If there are problems accessing or writing to the file.
     */
    public void addTask(Task newTask) throws DuckException {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(newTask.stringify() + "\n");
            writer.close();
        } catch (IOException e) {
            throw new DuckException("Error - unable to add task.");
        }
    }

    /**
     * Deletes a task (one line) from the file.
     * 
     * @param index The index of the task to be deleted.
     * @throws DuckException If there are problems accessing, reading or writing to the file.
     */
    public void deleteTask(int index) throws DuckException {
        ArrayList<String> history = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(new File(filePath));
            int lineCounter = 0;
            while (fileScanner.hasNextLine()) {
                if (lineCounter == index) {
                    fileScanner.nextLine();
                    lineCounter++;
                    continue;
                }
                lineCounter++;
                history.add(fileScanner.nextLine());
            }

            FileWriter writer = new FileWriter(filePath);
            while (history.size() > 0) {
                writer.write(history.remove(0) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DuckException("Error - unable to access task file.");
        }
    }
}
