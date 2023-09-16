package bert.storage;

import bert.tasks.Deadline;
import bert.tasks.Event;
import bert.tasks.Task;
import bert.tasks.TaskList;
import bert.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Deals with loading tasks from a file and saving tasks into a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage instance with a specified file path.
     *
     * @param filePath the file path to the file used for loading and saving tasks
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves a task list into the file.
     *
     * @param tasks the task list to be saved
     */
    public void save(TaskList tasks) {
        try {
            ensureTaskFileExists();
        } catch (IOException e) {
            System.out.println(
                    "____________________________________________________________\n" +
                    "OOPS!!! An error occurred while creating the task file.\n" +
                    "____________________________________________________________\n"
            );
            return;
        }
        try {
            writeToFile(filePath, tasks.toSaveFormat());
        } catch (IOException e) {
            System.out.println(
                    "____________________________________________________________\n" +
                    "OOPS!!! An error occurred while saving tasks.\n" +
                    "____________________________________________________________\n"
            );
        }
    }

    /**
     * Checks if the file at the specified file path exists.
     * If the directory or the file does not exist, creates the directory and the file.
     *
     * @throws IOException if an error occurs while creating the file
     */
    private void ensureTaskFileExists() throws IOException {
        File file = new File(this.filePath);
        if (!file.getParentFile().isDirectory()) {
            file.getParentFile().mkdir();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Writes the textToAdd string into a file specified by filePath.
     *
     * @param filePath the path to the file to be written into
     * @param textToAdd the string of text to be written into the file
     * @throws IOException if an error occurs while opening the file
     */
    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Loads tasks from the file into a List instance.
     *
     * @return a List instance containing the list of tasks read from the file
     * @throws FileNotFoundException if the file is not found
     */
    public List<Task> load() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        List<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            String task = sc.nextLine();
            switch (task.charAt(0)) {
                case 'T':
                    ToDo t = ToDo.createFromSaveFormat(task);
                    tasks.add(t);
                    break;
                case 'D':
                    Deadline d = Deadline.createFromSaveFormat(task);
                    tasks.add(d);
                    break;
                case 'E':
                    Event e = Event.createFromSaveFormat(task);
                    tasks.add(e);
                    break;
            }
        }
        sc.close();
        return tasks;
    }
}
