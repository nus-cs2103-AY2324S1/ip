package Storage;
import TaskList.TaskList;
import Parser.Parser;
import Task.*;
import Duke.DukeException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class Storage { // Previously named TaskListManger, serves same purpose
    private File file;
    private String taskDataPath;
    private TaskList taskList;

    public Storage(String directoryPath, String fileName, TaskList taskList) {
        this.taskList = taskList;
        this.taskDataPath = directoryPath + "/" + fileName;

        try {
            if (new File(directoryPath).mkdirs()) {
                System.out.println("Directories are created.");
            } else {
                System.out.println("Directories already exist.");
            }

            this.file = new File(taskDataPath);

            if (this.file.createNewFile()) {
                System.out.println("File is created: " + this.file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveTask(ArrayList<Task> tasks) {
        try {
            FileWriter w = new FileWriter(taskDataPath);
            for (Task task : tasks) {
                w.write(task.toString() + "\n");
            }
            w.close();
        } catch (IOException e) {
            System.out.println("Error occurred when saving tasks to file.");
        }
    }

    public void loadTasks() {
        try {
            File file = new File(taskDataPath);
            if (!file.exists()) {
                System.out.println("No such file");
                return;
            }
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String taskData = fileScanner.nextLine();
                Task task = Parser.parseTask(taskData);
                if (task != null) {
                    taskList.addTask(task);
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("Error occurred when loading tasks from file.");
        } catch (DukeException e) {
            System.out.println("File is corrupted :(");
        }
    }

}