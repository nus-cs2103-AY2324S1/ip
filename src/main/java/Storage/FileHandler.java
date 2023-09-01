package Storage;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import TaskManager.Tasks;
import TaskManager.Events;
import TaskManager.ToDos;
import TaskManager.Deadlines;

public class FileHandler {
    private static String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    public static ArrayList<Tasks> readTasksFromFile() {
        ArrayList<Tasks> task = new ArrayList<>();
        String folderPath = "data";

        // Use an absolute path for the data directory
        String absoluteFolderPath = Paths.get("").toAbsolutePath().toString() + "/" + folderPath;

        try {
            Files.createDirectories(Paths.get(absoluteFolderPath));
        } catch (IOException e) {
            System.out.println("Failed to create the directory: " + e.getMessage());
        }

        // Use an absolute path for the file
        String absoluteFilePath = Paths.get("").toAbsolutePath().toString() + "/" + filePath;
        Path path = Paths.get(absoluteFilePath);

        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                System.out.println("Failed to create the file: " + e.getMessage());
            }
        }

        try {
            FileReader reader = new FileReader(absoluteFilePath);
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

    public static void writeTasksToFile(TaskList task) {
        String absoluteFilePath = Paths.get("").toAbsolutePath().toString() + "/" + filePath;

        try {
            FileWriter writer = new FileWriter(absoluteFilePath);
            for (Tasks t : task.getTasks()) {
                writer.write(t.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {

        }
    }
}
