package chad.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import chad.exception.LoadException;
import chad.exception.SaveException;
import chad.task.Task;

/**
 * Represents a storage for the list of Tasks.
 */
public class Storage {
    private File file;

    /**
     * Constructor for Storage object.
     *
     * @param path The given path to the data file.
     */
    public Storage(String path) throws LoadException {
        this.file = new File(path);
        if (!this.file.getParentFile().exists()) {
            this.file.getParentFile().mkdirs();
        }
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                throw new LoadException();
            }
        }
    }

    /**
     * Returns an ArrayList of Tasks from local data file.
     *
     * @return ArrayList consisting of Task read from the data file.
     */
    public ArrayList<Task> load() throws LoadException {
        ArrayList<Task> list = new ArrayList<>();

        Scanner sc = null;

        try {
            sc = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to load local file");
        }

        while (sc != null && sc.hasNextLine()) {
            String input = sc.nextLine();
            Task task = Parser.parseFile(input);
            list.add(task);
        }

        return list;
    }

    /**
     * Saves the given TaskList into a local data file.
     *
     * @param taskList The given TaskList to be saved locally.
     */
    public void save(TaskList taskList) throws SaveException {
        assert taskList != null : "TaskList cannot be null";

        String newData = Parser.parseTaskListToData(taskList);

        try {
            FileWriter fw = new FileWriter(file);
            fw.write(newData);
            fw.close();
        } catch (IOException e) {
            throw new SaveException();
        }
    }

}
