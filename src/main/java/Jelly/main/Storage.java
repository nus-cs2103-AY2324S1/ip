package Jelly.main;

import Jelly.exceptions.JellyException;
import Jelly.task.Deadline;
import Jelly.task.Event;
import Jelly.task.Task;
import Jelly.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Stores and loads tasked based on the specified file path.
 */
public class Storage {
    private static final String FILE_PATH = "./taskData/jelly.txt";

    private String filePath;

    /**
     * Constructor for Storage, based on the specified file path.
     *
     * @param filePath The file path used when saving or starting up the bot. Contains a list of tasks(if any).
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Starts up the Jelly Bot by loading the tasks from the specified file path.
     *
     * @return An ArrayList with all the tasks from the file.
     * @throws JellyException If there is an error while loading up the file.
     */
    public ArrayList<Task> startUp() throws JellyException {
        ArrayList<Task> storage = new ArrayList<>();
        try {
            File data = new File(filePath);
            if (!data.exists()) {
                data.getParentFile().mkdirs();
                return storage;
            }
            int index = storage.size();
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                String[] taskDetails = str.split(" \\| ");
                Task task = new Task("");

                if (taskDetails[0].equals("T")) {
                    task = new Todo(taskDetails[2]);
                } else if (taskDetails[0].equals("D")) {
                    task = new Deadline(taskDetails[2], taskDetails[3]);
                } else if (taskDetails[0].equals("E")) {
                    String[] eventDetails = taskDetails[3].split(" to ");
                    task = new Event(taskDetails[2], eventDetails[0], eventDetails[1]);
                } else {
                    System.out.println("Unknown task in file");
                }

                if (taskDetails[1].equals("1")) {
                    task.markAsDone();
                } else {
                    task.markAsUndone();
                }
                storage.add(task);
                index++;
            }
            return storage;
        } catch (IOException e) {
            throw new JellyException("Error: " + e.getMessage());
        }
    }

    /**
     * Saves all task data into the file.
     *
     * @param taskList
     */
    public void saveAndExit(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);

            for (int i = 0; i < taskList.size(); i++) {
                fileWriter.write(taskList.get(i).writeToFile() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Could not save tasks: " + e.getMessage());
        }
    }
}
