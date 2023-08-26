package duke;

import duke.Task;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

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

