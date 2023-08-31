package duke;

import java.io.*;

import java.util.ArrayList;

public class Storage {
    java.nio.file.Path path = java.nio.file.Paths.get( "./","src", "main", "data", "duke.txt");
    private boolean fileExists = java.nio.file.Files.exists(path);
    File f = new File(String.valueOf(path));
    public static void saveTasksToFile(ArrayList<Task> tasks, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // data/duke.txt will only contain and ArrayList of Tasks
    public static ArrayList<Task> loadTasksFromFile(String filePath) {
        ArrayList<Task> tasks = new TaskList();
        File file = new File(filePath);
        if (file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
                tasks = (ArrayList<Task>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return tasks;
    }

    public boolean getFileExists() {
        return fileExists;
    }
}
