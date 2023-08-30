package storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import parser.DataParser;
import tasks.Task;

public class DataReader {

  private static String path;

  public DataReader(String path) {
    DataReader.path = path;
  }

  public static ArrayList<Task> readTasksFromFile() {
    ArrayList<Task> tasksList = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(DataReader.path))) {
      String line;
      while ((line = reader.readLine()) != null) {
        Task task = DataParser.parseLineToTask(line);
        if (task != null) {
          tasksList.add(task);
        }
      }
    } catch (IOException e) {
      System.out.println("Error reading the file or the file doesn't exist.");
      System.out.println("Please try again after ensuring the correctness of the file.");
      System.exit(1);
    }
    return tasksList;
  }
}
