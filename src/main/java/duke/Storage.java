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
            System.out.println("Trying to load existing task list in " + FILE_PATH);
            File f = new File(FILE_PATH);

            // Load file contents into task list
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                Task task = Parser.parseTaskListEntry(s.nextLine());
                if (task != null) {
                    taskList.addTask(task, false);
                }
            }

            taskList.printTasks();
        } catch (FileNotFoundException e) {
            // No file, create new one
            System.out.println("No existing task list found, creating a new file ./data/duke.txt");

            try {
                // Make directory
                File dataDir = new File("./data");
                if (!dataDir.exists()) {
                    dataDir.mkdir();
                }

                // Make file
                File f = new File(FILE_PATH);
                f.createNewFile();
                System.out.println("File created.");
            } catch (IOException e1) {
                System.out.println("Something went wrong: " + e.getMessage());
                return 0;
            }
        }

        return 1;
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
