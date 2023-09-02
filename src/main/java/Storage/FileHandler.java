package storage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import java.util.ArrayList;
import java.util.Scanner;

import taskmanager.Tasks;
import taskmanager.Events;
import taskmanager.ToDos;
import taskmanager.Deadlines;


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
    public static ArrayList<Tasks> readTasksFromFile() {
        ArrayList<Tasks> task = new ArrayList<>();
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
            boolean contentCheck = true;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!contentCheck) {
                    break;
                } else if (line.startsWith("T")) {
                    try {
                        String[] split = line.split(" \\| ");
                        if (split.length > 3) {
                            throw new IndexOutOfBoundsException("There is an error in your Todos content format!");
                        } if (split.length < 3) {
                            throw new IndexOutOfBoundsException("There is missing info for your Todos content in the file!");
                        }
                        ToDos newtodo = new ToDos(split[2], split[1]);
                        task.add(newtodo);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                        contentCheck = false;
                    }
                } else if (line.startsWith("D")) {
                    try {
                        String[] split = line.split(" \\| ");
                        if (split.length > 4) {
                            throw new IndexOutOfBoundsException("There is an error in your Deadlines content format!");
                        } if (split.length < 4) {
                            throw new IndexOutOfBoundsException("There is missing info for your Deadlines content in the file!");
                        }
                        Deadlines newdeadline = new Deadlines(split[1], split[2], split[3]);
                        task.add(newdeadline);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                        contentCheck = false;
                    }
                } else if (line.startsWith("E")) {
                    try {
                        String[] split = line.split(" \\| ");
                        if (split.length > 5) {
                            throw new IndexOutOfBoundsException("There is an error in your Events content format!");
                        } if (split.length < 5) {
                            throw new IndexOutOfBoundsException("There is missing info for your Events content in the file!");
                        }
                        Events newevent = new Events(split[1], split[2], split[3], split[4]);
                        task.add(newevent);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                    }
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
            for (Tasks t : tasks.getAllTasks()) {
                writer.write(t.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("File don't exist!!!");
        }
    }
}
