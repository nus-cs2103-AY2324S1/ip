package buddy.utils;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import buddy.*;

/**
 * The Storage class deals with loading tasks from the file and saving tasks to the file.
 *
 * @author Lim Jin Yin
 */
public class Storage {
    private String filePath = "./data/tasks.txt";

    /**
     * This is the constructor for a Storage.
     *
     * @param filePath The string representation of the file path.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the list of tasks by reading the file.
     *
     * @return Returns an array list of tasks.
     */
    public ArrayList<Task> readFile() {
        File file = new File(filePath);
        ArrayList<Task> taskList = new ArrayList<>();
        if (!file.exists()) {
            return taskList;
        }

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                taskList.addAll(parseTask(line).getAllTasks());
            }
        } catch (FileNotFoundException | BuddyException e) {
            throw new RuntimeException(e);
        }
        return taskList;

    }

    /**
     * Saves changes made to the task list by writing to the file.
     *
     * @param tasks The array list of tasks.
     */
    public void writeToFile(ArrayList<Task> tasks) { // save tasks
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                fileWriter.write(task.toSaveFileFormat() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private TaskList parseTask(String line) throws BuddyException {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        TaskList tasks = new TaskList();

        switch (taskType) {
            case "T":
                tasks.addTask(new Todo(description, isDone));
                break;

            case "D":
                String by = parts[3];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate deadlineBy = LocalDate.parse(by, formatter);
                tasks.addTask(new Deadline(description, deadlineBy, isDone));
                break;

            case "E":
                String start = parts[3];
                String end = parts[4];
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate startDate = LocalDate.parse(start, formatter);
                LocalDate endDate = LocalDate.parse(end, formatter);
                tasks.addTask(new Event(description, startDate, endDate, isDone));
                break;
        }

        return tasks;
    }
}
