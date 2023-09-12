package duke.logic;

import duke.exceptions.DukeException;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.util.ArrayList;

/**
 * Class to handle data writing and reading of task list to user hard disk.
 */
public class Storage {
    private String textFile;
    private String dataFile;

    /**
     * Constructor for Storage class.
     * @param textFile Name of the text file
     * @param dataFile Name of the data file
     */
    public Storage(String textFile, String dataFile) {
        this.textFile = textFile;
        this.dataFile = dataFile;
    }
    /**
     * Reads the data file and initializes the Task ArrayList
     * @return ArrayList<Task> based on data from the data file
     * @throws DukeException Error incurred when attempting to read the data file
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            FileInputStream fileIn = new FileInputStream(this.dataFile);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            @SuppressWarnings("unchecked")
            // can safely cast because all the methods to modify the array
            // guarantee that the elements in the array are all sub-classes
            // of Task, the array is type-safe
            ArrayList<Task> taskArr = (ArrayList<Task>) objIn.readObject();
            objIn.close();
            return taskArr;
        } catch (IOException e) {
            throw new DukeException("Data file is empty.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Saves the current task list into the text file
     * @param tasks TaskList that contains the list's data
     */
    public void saveTextFile(TaskList tasks) {
        try {
            ArrayList<Task> arr = tasks.retrieveArray();
            FileWriter fw = new FileWriter(textFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < arr.size(); i++) {
                bw.write(arr.get(i).toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Saves the current task list into the data file
     * @param tasks TaskList that contains the list's data
     */
    public void saveDataFile(TaskList tasks) {
        try {
            FileOutputStream fos = new FileOutputStream(dataFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks.retrieveArray());
            oos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
