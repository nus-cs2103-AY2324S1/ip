package duke.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
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
	 * Creates new Storage objects to store filpath and file
	 * 
	 * @param filePath Path of the tasks.txt file
	 */
    public Storage(String filePath) {
        this.file = new File(filePath);
        try {
            this.isCreated = !file.createNewFile();
        } catch (IOException e) {
            System.out.println("!ERROR! IOException" + e);
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
        } catch (FileNotFoundException e) {
            System.out.println("!ERROR! File is not found");
        } catch (IOException e) {
            System.out.println("!ERROR! " + e);
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
        } catch (FileNotFoundException e) {
            System.out.println("!ERROR! File is not found");
        } catch (IOException e) {
            System.out.println("!ERROR! " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("!ERROR! Class is not found");
        }
        return tasks;
    }

    public boolean fileExists() {
        return this.isCreated;
    }
}
