import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SaveHandler {
  private static final String SAVE_PATH = "./data/save.txt";

  public static List<Task> load() {
    try {
      File save = new File(SAVE_PATH);
      Scanner reader = new Scanner(save);
      List<Task> tasks = new ArrayList<>();

      while (reader.hasNextLine()) {
        String taskString = reader.nextLine();
        String[] taskElements = taskString.split("\\|");
        Task task;

        switch (taskElements[0]) {
        case "T":
          task = new Todo(taskElements[2]);
          break;
        case "D":
          task = new Deadline(taskElements[2], taskElements[3]);
          break;
        case "E":
          task = new Event(taskElements[2], taskElements[3], taskElements[4]);
          break;
        default:
          throw new UnsupportedOperationException();
        }

        if (taskElements[1].equals("1"))
          task.setDone();

        tasks.add(task);
      }
      reader.close();
      return tasks;
    } catch (Exception e) {
      System.out.println("Invalid save file");
      return new ArrayList<Task>();
    }
  }

  public static void save(List<Task> tasks) {
    try {
      File save = new File(SAVE_PATH);

      save.createNewFile();
      FileWriter writer = new FileWriter(save);
      for (Task task : tasks) {
        writer.write(task.save() + "\n");
      }
      writer.close();
    } catch (Exception e) {
      System.out.println("Failed to save");
    }
  }
}
