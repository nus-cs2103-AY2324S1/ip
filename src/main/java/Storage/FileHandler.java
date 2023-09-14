package storage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import java.util.ArrayList;
import java.util.Scanner;

import taskmanager.Deadline;
import taskmanager.Event;
import taskmanager.Task;
import taskmanager.ToDo;



/**
 * The `FileHandler` class handles reading tasks from and writing tasks to a file.
 */
public class FileHandler {
    private static String filePath; // The file path to the .txt file.

    /**
     * Constructs a `FileHandler` with the specified file path.
     *
     * @param filePath The path to the .txt file where tasks will be stored.
     */
    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads tasks from the .txt file and returns them as a list of tasks.
     *
     * @return An ArrayList containing the tasks read from the file.
     */
    public static ArrayList<Task> readTasksFromFile() {
        ArrayList<Task> task = new ArrayList<>();
        String folderPath = "data";

        try {
            Files.createDirectories(Paths.get(folderPath));
        } catch (IOException e) {
            System.out.println("Failed to create the directory: " + e.getMessage());
        }

        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                System.out.println("Failed to create the file: " + e.getMessage());
            }
        }

        try {
            FileReader reader = new FileReader(filePath);
            Scanner scanner = new Scanner(reader);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    if (line.startsWith("T")) {
                        parseToDoTask(line, task);
                    } else if (line.startsWith("D")) {
                        parseDeadlineTask(line, task);
                    } else if (line.startsWith("E")) {
                        parseEventTask(line, task);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                reader.close();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return task;
    }

    /**
     * Writes tasks to the specified file.
     *
     * @param tasks The TaskList containing the tasks to be written.
     */
    public static void writeTasksToFile(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task t : tasks.getAllTasks()) {
                writer.write(t.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("File don't exist!!!");
        }
    }

    private static void parseToDoTask(String line, ArrayList<Task> tasks) {
        try {
            String[] split = line.split(" \\| ");
            if (split.length != 3) {
                throw new IndexOutOfBoundsException("Invalid Todo task format in the file!");
            }
            ToDo newTodo = new ToDo(split[2], split[1]);
            tasks.add(newTodo);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void parseDeadlineTask(String line, ArrayList<Task> tasks) {
        try {
            String[] split = line.split(" \\| ");
            if (split.length > 4) {
                throw new IndexOutOfBoundsException(
                        "There is an error in your Deadlines content format!");
            }
            if (split.length < 4) {
                throw new IndexOutOfBoundsException(
                        "There is missing info for your Deadlines content in the file!");
            }
            Deadline newdeadline = new Deadline(split[1], split[2], split[3]);
            tasks.add(newdeadline);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void parseEventTask(String line, ArrayList<Task> tasks) {
        try {
            String[] split = line.split(" \\| ");
            if (split.length > 5) {
                throw new IndexOutOfBoundsException(
                        "There is an error in your Events content format!");
            }
            if (split.length < 5) {
                throw new IndexOutOfBoundsException(
                        "There is missing info for your Events content in the file!");
            }
            Event newevent = new Event(split[1], split[2], split[3], split[4]);
            tasks.add(newevent);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}

