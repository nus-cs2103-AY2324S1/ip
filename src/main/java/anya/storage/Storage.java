package anya.storage;

import anya.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import static anya.task.TaskType.getTaskType;

public class Storage {
    private final String storageFilePath;

    public Storage(String storageFilePath) {
        this.storageFilePath = storageFilePath;
    }

    public ArrayList<Task> load() throws IOException {
        File source = new File(storageFilePath);
        File directory = source.getParentFile();
        if (directory.mkdir()) {
            System.out.println("Directory was not found. New directory " + directory.getName() + " is created");
        }

        ArrayList<Task> tasks = new ArrayList<>();
        if (source.createNewFile()) {
            System.out.println("File is not found. New File created: " + source.getName());
        } else {
            System.out.println("File already exists. Your data is loaded");
            addTasksTo(tasks, source);
        }
        return tasks;
    }

    private void addTasksTo(ArrayList<Task> tasks, File source) throws FileNotFoundException {
        Scanner sc = new Scanner(source);
        while (sc.hasNext()) {
            String[] arguments = sc.nextLine().split("\\|");

            TaskType.Type taskType = getTaskType(arguments[0].trim());
            boolean isDone = arguments[1].trim().equals("1");
            String description = arguments[2].trim();
            LocalDateTime by = LocalDateTime.now();
            LocalDateTime from = LocalDateTime.now();
            LocalDateTime to = LocalDateTime.now();

            if (arguments.length == 4) {
                by = convertStringToDate(arguments[3].trim());
            } else if (arguments.length == 5) {
                from = convertStringToDate(arguments[3].trim());
                to = convertStringToDate(arguments[4].trim());
            }

            Task task;

            switch (taskType) {
                case TODO:
                    task = new Todo(description);
                    if (isDone) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                    break;
                case DEADLINE:
                    task = new Deadline(description, by);
                    if (isDone) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                    break;
                case EVENT:
                    task = new Event(description, from, to);
                    if (isDone) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                    break;
                default:
                    System.out.println("Unknown task type.");
                    break;
            }
        }
    }

    private void save(ArrayList<Task> tasks) {
        try {
            clearFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        for (Task t : tasks) {
            String text = "";
            try {
                text += t.formatToSave() + System.lineSeparator();
                appendToFile(text);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void clearFile() throws IOException {
        FileWriter fw = new FileWriter(this.storageFilePath, false);
        fw.write("");
        fw.close();
    }

    private void appendToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(this.storageFilePath, true);
        fw.write(text);
        fw.close();
    }

    private LocalDateTime convertStringToDate(String dateString) {
        return LocalDateTime.parse(dateString);
    }
}
