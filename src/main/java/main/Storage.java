package main;

import exception.DialogixException;
import task.Deadline;
import task.Event;

import task.Task;
import task.TaskType;
import task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "./data/dialogix.txt";

    public List<Task> loadTasks() throws DialogixException {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return tasks;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseSavedTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            scanner.close();
            return tasks;
        } catch (IOException e) {
            throw new DialogixException("Error loading tasks from file.");
        }
    }

    public void saveTasks(List<Task> tasks) throws DialogixException {
        try {
            File file = new File(FILE_PATH);
            File parentDirectory = file.getParentFile();

            if (!parentDirectory.exists()) {
                parentDirectory.mkdirs();
            }

            FileWriter fileWriter = new FileWriter(file);
            for (Task task : tasks) {
                fileWriter.write(task.toSaveString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DialogixException("Error saving tasks to file.");
        }
    }

    private Task parseSavedTask(String line) {
        String[] parts = line.split(" \\| ");
        TaskType taskType = TaskType.valueOf(parts[0]);
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
            case TODO:
                return new Todo(description, isDone);
            case DEADLINE:
                LocalDate byDate = LocalDate.parse(parts[3]);
//                Priority priority = Priority.valueOf(parts[4]);
                return new Deadline(description, byDate, null, isDone);
            case EVENT:
                LocalDate fromDate = LocalDate.parse(parts[3]);
                LocalDate toDate = LocalDate.parse(parts[4]);
                return new Event(description, fromDate, toDate);
            default:
                return null;
        }
    }
}
