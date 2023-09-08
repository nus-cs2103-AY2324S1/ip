package bot.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import bot.exceptions.InvalidTaskException;
import bot.exceptions.LoadingException;
import bot.utils.tasks.Task;

/**
 * Abstraction for storing and loading data from a local file.
 */
public class Storage {
    /**
     * File path for loading and saving data.
     */
    private final String filePath;

    /**
     * Creates a Storage object that stores and loads data at that file path.
     *
     * @param filePath Path to file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads data from that file path, and creates the file if it doesn't exist.
     *
     * @param ui UI for displaying messages.
     * @return ArrayList of Task objects.
     * @throws LoadingException If the data file cannot be read or created.
     */
    public ArrayList<Task> load(Ui ui) throws LoadingException {
        File f = new File(this.filePath);
        ArrayList<Task> tasks;
        try {
            if (!f.isFile()) {
                createTasksFile(f);
            }
            tasks = loadTasksFromFile(f, ui);
        } catch (IOException | SecurityException e) {
            throw new LoadingException(e.getMessage());
        }
        return tasks;
    }

    /**
     * Creates the task file in the specified directory given by the file's filepath.
     *
     * @param f Details of file to create.
     * @throws IOException If the file cannot be created.
     */
    private static void createTasksFile(File f) throws IOException {
        File parent = new File(f.getParent());
        if (!parent.isDirectory()) {
            parent.mkdirs();
        }
        f.createNewFile();
    }

    /**
     * Creates an ArrayList of tasks from the contents of the file, printing to the Ui object given.
     *
     * @param f File to read tasks from.
     * @param ui UI to print to.
     * @return Tasks in an ArrayList.
     * @throws FileNotFoundException If file cannot be found.
     */
    private static ArrayList<Task> loadTasksFromFile(File f, Ui ui) throws FileNotFoundException {
        Scanner scanner = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            try {
                tasks.add(Task.convertFromDataString(scanner.nextLine()));
            } catch (InvalidTaskException e) {
                System.out.println("Invalid task, skipping...");
            }
        }
        return tasks;
    }

    /**
     * Saves task list to file named at filePath. Assumes the file has been created.
     *
     * @param lst Bot.Task list to save to file.
     * @throws LoadingException If the list cannot be saved fully.
     */
    public void save(TaskList lst) throws LoadingException {
        assert lst != null : "lst must not be null";
        try {
            File f = new File(this.filePath);
            FileWriter fw = new FileWriter(f);
            // We did it like this so the last line isn't a line separator.
            for (int i = 1; i < lst.size(); i++) {
                fw.write(lst.get(i).convertToDataString());
                fw.write(System.lineSeparator());
            }
            if (lst.size() > 0) {
                fw.write(lst.get(lst.size()).convertToDataString());
            }
            fw.close();
        } catch (IOException e) {
            throw new LoadingException(e.getMessage());
        }
    }
}
