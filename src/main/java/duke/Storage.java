package duke;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;

class Storage {
    private String textFile;
    private String dataFile;
    public Storage(String textFile, String dataFile) {
        this.textFile = textFile;
        this.dataFile = dataFile;
    }
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
     * Saves all the tasks' information in a text file.
     */
    public void writeToFile(TaskList tasks) {
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
    public void save(TaskList tasks) {
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
