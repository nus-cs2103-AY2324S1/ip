package bot.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import bot.exceptions.InvalidTaskException;
import bot.exceptions.LoadingException;

/**
 * Abstraction for storing and loading data from a local file.
 */
public class Storage {
    /**
     * File path for loading and saving data.
     */
    private String filePath;

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
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(this.filePath);
        File parent = new File(f.getParent());
        try {
            if (f.isFile()) {
                Scanner scanner = new Scanner(f);
                while (scanner.hasNextLine()) {
                    try {
                        tasks.add(Task.convertFromString(scanner.nextLine()));
                    } catch (InvalidTaskException e) {
                        ui.println("Invalid task, skipping...");
                    }
                }
            } else {
                ui.println("No data found, creating...");
                if (!parent.isDirectory()) {
                    parent.mkdirs();
                }
                f.createNewFile();
            }
        } catch (IOException | SecurityException e) {
            throw new LoadingException(e.getMessage());
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
        try {
            File f = new File(this.filePath);
            FileWriter fw = new FileWriter(f);
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
