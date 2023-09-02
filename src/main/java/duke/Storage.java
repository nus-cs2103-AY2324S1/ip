package duke;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/**
 * The storage class to handle data saving
 */
public class Storage {
    private final String filepath;

    /**
     * constructor for filepath
     * @param filepath key in where you want to store
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * save a arrlist to the file
     * @param arr the arraylist it takes in
     */
    public void saveDataToFile(ArrayList<Task> arr) {
        try {
            File file = new File(filepath);
            if (!file.exists()) {
                file.createNewFile();
            }

            try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(file))) {
                objectOut.writeObject(arr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * loads data from the saved file
     * @return the saved arrlist
     */
    public ArrayList<Task> loadDataFromFile() {
        ArrayList<Task> arr = new ArrayList<>();
        try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(filepath))) {
            ArrayList<?> temp = (ArrayList<?>) objectIn.readObject();
            for (Object obj : temp) {
                if (obj instanceof Task) {
                    arr.add((Task) obj);
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return arr;
    }


}

