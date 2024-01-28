package duke.storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import duke.helper.DukeException;
import duke.helper.Ui;
import duke.tasks.Task;

/**
 * Storage class for import and export between txt file and TaskList.
 */
// Solution below adapted by
// https://github.com/jean-cq/ip/blob/master/src/main/java/urchatbot/storage/Storage.java
public class Storage {
    private static String path;
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    /**
    * set the file path to the given path
    *
    * @param filePath the given file path
    */
    public static void setPath(String filePath) {
        path = filePath;
    }

    /**
    * save the arraylist of tasks in txt format
    *
    * @param tasks the arraylist of tasks to be saved
    */
    public static void save(ArrayList<Task> tasks) {
        try (PrintWriter printwriter = new PrintWriter(new FileWriter(path))) {
            for (Task task : tasks) {
                printwriter.write(task.toFile() + "\n");
            }
        } catch (IOException e) {
            System.out.println("There is an error saving this file: " + e.getMessage());
        }
    }

    /**
    * load tasks in txt format into an arraylist
    *
    * @return an arraylist translated from the txt file
    */
    // @@author infiBeyond-reused
    // Reused from Chen Qun's iP load method with minor modifications
    // Link to Chen Qun's implementation:
    // https://github.com/jean-cq/ip/blob/master/src/main/java/urchatbot/storage/Storage.java
    // load method
    public static ArrayList<Task> load() throws DukeException {
        handleMissing(path);

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String text = reader.readLine();
            while (text != null) {
                Task task = Task.convertStringToTask(text);
                taskList.add(task);
                text = reader.readLine();
            }
        } catch (IOException e) {
            Ui.print("There is an error loading tasks from file: " + e.toString());
        }
        return taskList;
    }
    // @@author

    /**
    * handles missing txt file by creating a new one
    *
    * @param testPath the file path to test if exist
    */
    private static void handleMissing(String testPath) {
        try {
            //if directory or path doesn't exist
            Path directoryPath = Paths.get(".", "data");
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            Path path = directoryPath.resolve("tasks.txt");
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There is an error loading file: " + e.getMessage());
        }
    }
}
