package duke.storage;

import duke.commands.Parser;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        Task task = null;

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                task = Parser.parseFileString(scanner.nextLine());
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            Ui.showMessage("Data file not found, starting with an empty task list.");
        }
        return tasks;
    }

    public void saveTasks(TaskList tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (int i = 0; i < tasks.getTaskCount(); i++) {
                writer.println(Parser.readTaskToFile(tasks.getTask(i)));
            }
        } catch (IOException e) {
            Ui.showErrorMessage("Error saving tasks to file: " + e.getMessage());
        }
    }
}
