import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DukeIO {
  private final Path taskFilePath;
  private final Path dukeDir;

  public DukeIO(String fileName) {
    String home = System.getProperty("user.home");
    this.dukeDir = Paths.get(home, "duke");
    this.taskFilePath = Paths.get(home, "duke", fileName);

    if (!Files.exists(dukeDir)) {
      try {
        Files.createDirectories(dukeDir);
      } catch (IOException e) {
        System.out.println("Failed to create duke directory");
      }
    }
  }

  private static final Pattern taskPattern = Pattern.compile("^\\[([TDE])\\]\\[[X\\s]\\]\\s(.+)");
  private static final Pattern todoPattern = Pattern.compile("^\\[T\\]\\[[X\\s]\\]\\s+(.+)$");
  private static final Pattern deadlinePattern =
      Pattern.compile("^\\[D\\]\\[[X\\s]\\]\\s+(.+)\\s+\\(by:\\s+(.+)\\)$");
  private static final Pattern eventPattern =
      Pattern.compile("^\\[E\\]\\[[X\\s]\\]\\s+(.+)\\s+\\(from:\\s+(.+)\\s+to:\\s+(.+)\\)$");

  private void handleTodo(String input, ArrayList<Task> tasks) throws DukeException {
    Matcher m = todoPattern.matcher(input);
    if (m.find()) {
      TodoTask newTask = new TodoTask(m.group(1));
      tasks.add(newTask);
    } else {
      throw new DukeException("Todo Task file is corrupt");
    }
  }

  private void handleDeadline(String input, ArrayList<Task> tasks) throws DukeException {
    Matcher m = deadlinePattern.matcher(input);
    if (m.find()) {
      TodoTask newTask = new TodoTask(m.group(1));
      tasks.add(newTask);
    } else {
      throw new DukeException("Deadline Task file is corrupt");
    }
  }

  private void handleEvent(String input, ArrayList<Task> tasks) throws DukeException {
    Matcher m = eventPattern.matcher(input);
    if (m.find()) {
      TodoTask newTask = new TodoTask(m.group(1));
      tasks.add(newTask);
    } else {
      throw new DukeException("Event Task file is corrupt");
    }
  }

  public ArrayList<Task> readTasks() throws DukeException, IOException {
    ArrayList<Task> tasks = new ArrayList<>();
    if (!Files.exists(taskFilePath)) {
      throw new FileNotFoundException();
    }
    List<String> lines = Files.readAllLines(taskFilePath);
    for (String line : lines) {
      Matcher m = taskPattern.matcher(line);
      if (!m.find()) {
        throw new DukeException("Task file is corrupt");
      }
      String type = m.group(1);
      switch (type) {
        case "T":
          handleTodo(line, tasks);
          break;
        case "D":
          handleDeadline(line, tasks);
          break;
        case "E":
          handleEvent(line, tasks);
          break;
      }
    }
    return tasks;
  }

  public void saveToFile(ArrayList<Task> tasks) {
    try {
      List<String> taskStrings = tasks.stream().map(Task::toString).collect(Collectors.toList());
      Files.write(
          taskFilePath,
          taskStrings,
          StandardOpenOption.CREATE,
          StandardOpenOption.TRUNCATE_EXISTING);
    } catch (IOException e) {
      System.out.println("Failed to save to file: " + e);
    }
  }
}
