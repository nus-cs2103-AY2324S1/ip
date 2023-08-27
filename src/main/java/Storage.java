import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Storage {
  private static final Path SAVE_FOLDER = Paths.get(".", "data");
  private static final Path SAVE_PATH = Paths.get(SAVE_FOLDER.toString(),
      "save.txt");

  public static List<Task> load() {
    try {
      List<String> save = Files.readAllLines(SAVE_PATH);
      List<Task> tasks = new ArrayList<>();

      for (String taskString : save) {
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
      System.out.println("List loaded");
      return tasks;
    } catch (IOException e) {
      System.out.println("Save file not found");
    } catch (Exception e) {
      System.out.println("Corrupted save file");
    }

    return new ArrayList<Task>();
  }

  public static void save(List<Task> tasks) {
    try {
      Files.createDirectories(SAVE_FOLDER);
      Files.createFile(SAVE_PATH);
    } catch (IOException e) {
    }

    try {
      String saveString = "";
      for (Task task : tasks) {
        saveString += task.save() + "\n";
      }

      byte[] saveBytes = saveString.getBytes();
      Files.write(SAVE_PATH, saveBytes);
      System.out.println("List saved");
    } catch (Exception e) {
      System.out.println("Failed to save");
    }
  }
}
