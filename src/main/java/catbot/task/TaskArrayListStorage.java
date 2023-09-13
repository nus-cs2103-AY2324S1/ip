package catbot.task;

import java.io.*;
import java.util.ArrayList;

public class TaskArrayListStorage {

    private String path;

    public TaskArrayListStorage(String path) {
        this.path = path;
    }

    //region FileIO

    public void writeSerializedToFile(ArrayList<Task> taskArrayList) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(path));
            output.writeObject(taskArrayList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Task> readSerializedFromFile() {
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(path));
            Object readObject = input.readObject();
            @SuppressWarnings("unchecked")
            ArrayList<Task> tasks = (ArrayList<Task>) readObject;
            input.close();
            return tasks;
        } catch (IOException ignored) {
        } catch (ClassNotFoundException e) { //save corrupted
            throw new RuntimeException(e);
        }

        return new ArrayList<>();
    }

    //endregion
}
