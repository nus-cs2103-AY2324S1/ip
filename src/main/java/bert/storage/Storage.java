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
     * @param filePath The file path to the file used for loading and saving tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves a task list into the file.
     *
     * @param tasks The task list to be saved.
     * @throws IOException If an error occurs while creating the file or saving tasks.
     */
    public void save(TaskList tasks) throws IOException {
        ensureTaskFileExists();
        writeToFile(filePath, tasks.toSaveFormat());
    }

    /**
     * Checks if the file at the specified file path exists.
     * If the directory or the file does not exist, creates the directory and the file.
     *
     * @throws IOException If an error occurs while creating the file.
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
     * @param filePath The path to the file to be written into.
     * @param textToAdd The string of text to be written into the file.
     * @throws IOException If an error occurs while opening the file.
     */
    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Loads tasks from the file into a List instance.
     *
     * @return A List instance containing the list of tasks read from the file.
     * @throws FileNotFoundException If the file is not found.
     */
    public List<Task> load() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner fileScanner = new Scanner(file);
        List<Task> tasks = new ArrayList<>();

        while (fileScanner.hasNext()) {
            String formattedTask = fileScanner.nextLine();
            switch (formattedTask.charAt(0)) {
            case 'T':
                ToDo t = ToDo.createFromSaveFormat(formattedTask);
                tasks.add(t);
                break;
            case 'D':
                Deadline d = Deadline.createFromSaveFormat(formattedTask);
                tasks.add(d);
                break;
            case 'E':
                Event e = Event.createFromSaveFormat(formattedTask);
                tasks.add(e);
                break;
            default:
                assert false : "Task in save file does not start with T, D or E";
            }
        }

        fileScanner.close();
        return tasks;
    }
}
