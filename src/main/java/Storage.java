import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Storage {
  public Storage() {}

  private final String SAVE_DATA_PATH = "./data/Duke.txt";

  private TimeParser parser = new TimeParser();

  public void saveFile(TaskList tasks) {
    try {
      FileWriter fw = new FileWriter(SAVE_DATA_PATH);
      for (Task task : tasks.getTaskList()) {
        fw.write(task.saveData() + "\n");
      }
      fw.close();
    } catch (IOException e) {
      System.out.println("     File not found, unable to save");
    }
  }

  public LinkedList<Task> loadFile() {
    try {
      LinkedList<Task> tasks = new LinkedList<>();
      File f = new File(SAVE_DATA_PATH);
      f.createNewFile();
      Scanner scanner = new Scanner(f);
      while (scanner.hasNext()) {
        String s = scanner.nextLine();
        char delimiter = 31;
        String[] taskData = s.split(String.valueOf(delimiter), -1);
        boolean isComplete = taskData[1].equals("1");
        switch (taskData[0]) {
          case "event":
            tasks.add((new EventTask(taskData[2], parser.parseTime(taskData[3]), parser.parseTime(taskData[4]), isComplete)));
            break;
          case "todo":
            tasks.add(new ToDoTask(taskData[2], isComplete));
            break;
          default:
            tasks.add(new DeadlineTask(taskData[2], parser.parseTime(taskData[3]), isComplete));
        }
      }
      return tasks;
    } catch (IOException | InvalidDateException e2) {
      System.out.println("     Unable to load/find file, using an empty task list");
      return new LinkedList<>();
    }
  }
}
