package horo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import horo.data.Task;
import horo.data.TaskList;

public class Storage {

  private static final String DEFAULT_STORAGE_FILEPATH = "./data/tasks.txt";

  public final String filePath;

  public Storage() {
    this(DEFAULT_STORAGE_FILEPATH);
  }

  public Storage(String filePath) {
    this.filePath = filePath;
  }

  public TaskList load() {
    ArrayList<Task> tList = new ArrayList<>();
    try {
      File taskFile = new File(filePath);
      if (!taskFile.exists()) {
        taskFile.mkdirs();
      }

      if (taskFile.createNewFile()) {
        System.out.println("File created: " + taskFile.getName());
      }

      Scanner scanner = new Scanner(taskFile);
      while (scanner.hasNextLine()) {
        String data = scanner.nextLine();
        tList.add(Parser.parseDataString(data));
      }

      scanner.close();
    } catch (IOException e) {
      System.out.println("Error writing to file");
      e.printStackTrace();
    } catch (HoroException e) {
      System.out.println(e.getMessage());
    }
    return new TaskList(tList);
  }

  public void updateTaskData(TaskList taskList) {
    try {
      FileWriter writer = new FileWriter(filePath, false);
      for (Task t : taskList.getTasks()) {
        writer.write(t.getDataString() + "\n");
      }
      writer.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
