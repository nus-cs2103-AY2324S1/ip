package duck;

import duck.task.Task;
import duck.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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

    // Appends instead of rewriting the file
    public void addTask(Task newTask) throws DuckException {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(newTask.stringify() + "\n");
            writer.close();
        } catch (IOException e) {
            throw new DuckException("Error - unable to add task.");
        }
    }

    // Rewrites the file, except for the line at the specified index
    public void deleteTask(int index) throws DuckException {
        ArrayList<String> history = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(new File(filePath));
            int lineCounter = 1;
            while (fileScanner.hasNextLine()) {
                if (lineCounter == index) {
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
