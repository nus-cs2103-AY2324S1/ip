package anya.storage;

import anya.task.Deadline;
import anya.task.Event;
import anya.task.Task;
import anya.task.TaskList;
import anya.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String storageFilePath;

    public Storage(String path) throws InvalidStorageFilePathException {
        this.storageFilePath = path;
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
    }

    /**
     * Returns true if the given path is acceptable as a storage file.
     * The file path is considered acceptable if it ends with '.txt'
     */
    private static boolean isValidPath(String filePath) {
        return filePath.endsWith(".txt");
    }

    public TaskList load() throws StorageOperationException {
        // Check for directory
        File source = new File(storageFilePath);
        File directory = source.getParentFile();
        if (directory.mkdir()) {
            System.out.println("Directory was not found. New directory " + directory.getName() + " is created");
        }
        try {
            if (source.createNewFile()) {
                System.out.println("File is not found. New File created: " + source.getName());
                return new TaskList();
            } else {
                System.out.println("File already exists. Your data is loaded");
                return readFile();
            }
        } catch (IOException e) {
            throw new StorageOperationException("Error writing to file: " + storageFilePath);
        }
    }

    public TaskList readFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(storageFilePath);
        while (sc.hasNext()) {
            try {
                tasks.add(convertStringToTask(sc.nextLine()));
            } catch (UnknownTaskException e) {
                System.out.println(e.getMessage());
            }
        }
        return new TaskList(tasks);
    }

    public Task convertStringToTask(String input) throws UnknownTaskException {
        String[] args = input.split("\\|");

        String taskType = args[0].trim();
        boolean isDone = args[1].trim().equals("1");
        String description = args[2].trim();

        if (taskType.equals("T")) {
            return new Todo(description, isDone);
        } else if (taskType.equals("D")) {
            String by = args[3].trim();
            return new Deadline(description, by, isDone);
        } else if (taskType.equals("E")) {
            String from = args[3].trim();
            String to = args[4].trim();
            return new Event(description, from, to, isDone);
        } else {
            throw new UnknownTaskException("Unknown task identified: " + taskType);
        }
    }

    public void save(TaskList tasks) {
        try {
            clearFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < tasks.size(); i++) {
            String text = "";
            Task t = tasks.get(i);
            try {
                text += convertTaskToString(t) + System.lineSeparator();
                appendToFile(text);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String convertTaskToString(Task task) {
        return task.formatToSave();
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

    /**
     * Signals that the given file path does not fulfill the storage filepath constraints.
     */
    public static class InvalidStorageFilePathException extends Exception {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    /**
     * Signals that some error has occured while trying to convert and read/write data between the application
     * and the storage file.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }

    public static class UnknownTaskException extends Exception {
        public UnknownTaskException(String message) {
            super(message);
        }
    }
}
