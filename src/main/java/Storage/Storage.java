package Storage;

import TaskList.TaskList;
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

    public static void addLine(String line) {
        DataWriter.addLine(line);
    }

    public static void refresh(TaskList taskList) {
        DataWriter.refresh(taskList);
    }

    public static ArrayList<Task> readFileToTasksLists(String fileName) {
        return DataReader.readTasksFromFile(fileName);
   }
}
