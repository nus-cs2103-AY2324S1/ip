package storage;

import java.util.ArrayList;
import tasklist.TaskList;
import tasks.Task;

public class Storage {
  private static String path;

  private DataReader dataReader;

  private DataWriter dataWriter;

  public Storage(String path) {
    Storage.path = path;
    this.dataReader = new DataReader(path);
    this.dataWriter = new DataWriter(path);
  }

  public static void addLine(String line) {
    DataWriter.addLine(line);
  }

  public static void deleteLine(int pos) {
    DataWriter.deleteLine(pos);
  }

  public static void refresh(TaskList taskList) {
    DataWriter.refresh(taskList);
  }

  public static ArrayList<Task> readFileToTasksLists(String fileName) {
    return DataReader.readTasksFromFile();
  }
}
