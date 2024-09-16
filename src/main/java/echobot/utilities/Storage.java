package echobot.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Handles loading and saving tasks into the tasks.txt file
 */
public class Storage {

    /** Variable to store relative file path */
    private File file;

    /** Variable to check if File already exists */
    private boolean isCreated;

    /**
     * Creates a new Storage object to store filepath and file
     *
     * @param filePath Path of the tasks.txt file
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        try {
            this.isCreated = !file.createNewFile();
        } catch (IOException e) {
            AlertBox alert = new AlertBox("IOException", "File not found or corrupted (Storage constructor)");
            alert.show();
        }
    }

    /**
     * Overwrites existing data in tasks.txt
     *
     * @param tasks List of tasks that will overwrite the data in the file
     */
    public void overwriteTasksData(ArrayList<Task> tasks) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            AlertBox alert = new AlertBox("File not found or corrupted", "Please try to restart the app");
            alert.show();
        }
    }

    /**
     * Loads the data from tasks.txt
     *
     * @return A list of tasks
     */
    public ArrayList<Task> loadTasksData() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tasks = (ArrayList<Task>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            AlertBox alert = new AlertBox("File not found or corrupted", "Please try to restart the app");
            alert.show();
        } catch (ClassNotFoundException e) {
            AlertBox alert = new AlertBox("ClassNotFoundException", "Class is not found, please restart the app");
            alert.show();
        }
        return tasks;
    }

    /**
     * Checks whether file already exists or not
     *
     * @return Value of isCreated
     */
    public boolean fileExists() {
        return this.isCreated;
    }
}
