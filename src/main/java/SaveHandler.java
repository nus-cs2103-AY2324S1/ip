import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SaveHandler {
  private static final String SAVE_PATH = "./data/save.txt";

  public static List<Task> load() {
    return new ArrayList<Task>();
  }

  public static void save(List<Task> tasks) {
    try {
      File save = new File(SAVE_PATH);

      save.createNewFile();
      FileWriter writer = new FileWriter(save);
      for (Task task : tasks) {
        writer.write(task.save() + "\n");
        System.out.print("savepathereh");
      }
      writer.close();
    } catch (Exception e) {
      System.out.println("Failed to save");
    }
  }
}
