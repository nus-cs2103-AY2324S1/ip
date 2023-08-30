package Storage;

import Tasks.Task;

import java.util.ArrayList;

public class Storage {
    private static String path;

    private DataReader dataReader;

    private DataWriter dataWriter;

    public Storage(String path) {
        Storage.path = path;
        this.dataReader = new DataReader();
        this.dataWriter = new DataWriter(path);
    }

   public static ArrayList<Task> readFileToTasksLists(String fileName) {
        return DataReader.readTasksFromFile(fileName);
   }
}
