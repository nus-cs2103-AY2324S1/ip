package Iris;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The class responsible for loading and saving tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor for the Storage class.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from a file.
     *
     * @return An ArrayList of Task objects loaded from the file.
     */
    public ArrayList<Task> loadTask() {
        File file = new File(this.filePath);
        ArrayList<Task> toDoList = new ArrayList<Task>();
        try {
            if (file.exists()) {
                BufferedReader fileReader = new BufferedReader(new FileReader(file));
                String line = fileReader.readLine();

                while (line != null) {
                    Task task = Task.readTaskFromFile(line);
                    toDoList.add(task);
                    line = fileReader.readLine();
                }

                fileReader.close();
            } else {
                System.out.println("Looks like this is your first time here!");
                System.out.println("Iris will save your files in iris.txt.");
                boolean fileCreated = file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toDoList;
    }

    /**
     * Writes tasks to a file.
     *
     * @param toDoList The ToDoList containing tasks to be saved to the file.
     */
    public void writeTask(ToDoList toDoList) {
        try (FileWriter fileWriter = new FileWriter("iris.txt")) {
            for (int i = 1; i <= toDoList.size(); i++) {
                Task task = toDoList.get(i);
                fileWriter.write(task.writeTaskToFile() + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error has occurred whilst writing to file. " +
                    "Error:" + e.getMessage());
        }
    }
}
