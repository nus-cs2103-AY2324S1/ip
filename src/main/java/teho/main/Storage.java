package teho.main;

import teho.main.Parser;
import teho.main.Task;
import teho.main.TaskList;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Saves and loads tasks into and from a file.
 */
public class Storage {
    /** File path for saving and loadings tasks into and from a file */
    public static String filePath;

    /**
     * Constructs new Storage instance with file path.
     *
     * @param filePath String representation of the file path of the file
     *                 to save and load task into and from.
     */
    public Storage(String filePath) {
        assert filePath != null: "Path of file should not be a null";
        this.filePath = filePath;
    }

    /**
     * Saves tasks from taskList into file.
     *
     * @param taskList List of tasks to save the tasks from into the file.
     */
    public static void saveTasks(TaskList taskList) {
        assert taskList != null: "taskList should not be a null";
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < taskList.getSize(); i++) {
                Task task = taskList.getTask(i);
                fw.write(task.fileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Ohno! Error when saving task(s).");
        }
    }

    /**
     * Loads tasks from file into a taskList.
     *
     * @return ArrayList containing the loaded tasks.
     */
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> taskList =  new ArrayList<>();
        try {
            File loadedFile = new File(filePath);
            Scanner sc = new Scanner(loadedFile);
            while (sc.hasNext()) {
                String nextLine = sc.nextLine();
                Parser.readLine(nextLine, taskList);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ohno! Error when loading task(s).");
        }
        return taskList;
    }
}
