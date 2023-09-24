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

public class Storage {
    private static String path;
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void setPath(String filePath) {
        path = filePath;
    }
    
    public static void save(ArrayList<Task> tasks) {
        try (PrintWriter printwriter = new PrintWriter(new FileWriter(path))) {
            for (Task task : tasks) {
                printwriter.write(task.toFile() +"\n");
            }
        } catch (IOException e) {
            System.out.println("There is an error saving this file: " + e.getMessage());
        }
    }

    public static ArrayList<Task> load() throws DukeException{
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

    private static void handleMissing(String testPath) {
        try{
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
