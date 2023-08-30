package duke;

import duke.task.Task;
import java.io.*;
import java.util.ArrayList;

public class Storage {
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            FileOutputStream file = new FileOutputStream("savedTasks.ser");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(tasks);
            output.close();
            file.close();
        } catch (IOException e) {
            System.out.println("file error occurred when saving");
        }
    }

    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        try {
            FileInputStream file = new FileInputStream("savedTasks.ser");
            @SuppressWarnings("unchecked")
            ObjectInputStream output = new ObjectInputStream(file);
            ArrayList<Task> tasks = (ArrayList<Task>) output.readObject();
            output.close();
            file.close();
            return tasks;
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
