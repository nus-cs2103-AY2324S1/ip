package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Encapsulates read and write methods that deal with the task list file.
 */
public class Storage {
    public static final String FILE_PATH = "./data/duke.txt";

    /**
     * Loads the existing task list file and creates one if it does not exist.
     * @param taskList task list managed by the chatbot to populate
     * @return process code, where 0 means no file was read / created and 1 otherwise
     */
    public static int initialiseTaskListFile(TaskList taskList) {
        try {
            // Load existing file
            File f = getFile(FILE_PATH);
            loadTaskListFromFile(taskList, f);
            taskList.printTasks();
            return 1;
        } catch (FileNotFoundException e) {
            return handleFileNotFound();
        }
    }

    private static File getFile(String filePath) throws FileNotFoundException {
        System.out.println("Trying to load existing task list in " + filePath);
        File f = new File(filePath);
        return f;
    }

    private static void loadTaskListFromFile(TaskList taskList, File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            Task task = Parser.parseTaskListEntry(s.nextLine());
            if (task != null) {
                taskList.addTask(task, false);
            }
        }
    }

    private static int handleFileNotFound() {
        System.out.println("No existing task list found, creating a new file ./data/duke.txt");

        try {
            makeDirectory("./data");
            makeFile(FILE_PATH);
            return 1;
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            return 0;
        }
    }

    private static void makeDirectory(String directory) throws IOException {
        File dataDir = new File(directory);
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        System.out.println("Directory created.");
    }

    private static void makeFile(String filePath) throws IOException {
        File f = new File(filePath);
        f.createNewFile();
        System.out.println("File created.");
    }

    /**
     * Updates task list file based on user's action
     * @param taskList most updated task list used to edit the task list file
     */
    public static void updateTaskListFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            StringBuilder tasksString = new StringBuilder();
            for (int i = 0; i < taskList.getCount(); i++) {
                String task = taskList.getTask(i).toTaskListEntry();
                tasksString.append(task + "\n");
            }

            fw.write(tasksString.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong while updating file: " + e.getMessage());
        }
    }
}
