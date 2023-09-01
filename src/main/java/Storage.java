import java.io.*;
import java.util.List;
import java.util.ArrayList;
//The Storage class is in charge of writing and reading the tasklists
//from the Duke.txt file
public class Storage {
    static void saveTasks(String filePath, List<Task> tasks) {
        ObjectOutputStream file = null;
        try {
            file = new ObjectOutputStream(new FileOutputStream(filePath));
            file.writeObject(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (file!= null) {
                try {
                    file.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    static List<Task> loadTasks(String filePath) {
        List<Task> tasks = new ArrayList<>();
        ObjectInputStream file = null;
        try {
            @SuppressWarnings("unchecked")
            //Unchecked warnings are yielded due to an unchecked type conversion
            //readObject() returns an Object, but casting it to List<Task>
            //The compiler has no way to check if the object is of type List<Task>
            //in compile time, resulting in an unchecked warning being yielded
            File taskFile = new File(filePath);
            if (taskFile.length() > 0) { //check if file is empty before attempting to read the file
                file = new ObjectInputStream(new FileInputStream(taskFile));
                tasks = (List<Task>) file.readObject();
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return tasks;
    }
}
