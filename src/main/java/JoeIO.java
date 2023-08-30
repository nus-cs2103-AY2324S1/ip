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

public class JoeIO {
  private final Path taskFilePath;

  public JoeIO(String fileName) {
    this.taskFilePath = Paths.get(fileName);
  }

  private static final Pattern taskPattern = Pattern.compile("^\\[([TDE])\\]\\[[X\\s]\\]\\s(.+)");
  private static final Pattern todoPattern = Pattern.compile("^\\[T\\]\\[[X\\s]\\]\\s+(.+)$");
  private static final Pattern deadlinePattern =
      Pattern.compile("^\\[D\\]\\[[X\\s]\\]\\s+(.+)\\s+\\(by:\\s+(.+)\\)$");
  private static final Pattern eventPattern =
      Pattern.compile("^\\[E\\]\\[[X\\s]\\]\\s+(.+)\\s+\\(from:\\s+(.+)\\s+to:\\s+(.+)\\)$");

  private void handleTodo(String input, ArrayList<Task> tasks) throws JoeException {
    Matcher m = todoPattern.matcher(input);
    if (m.find()) {
      TodoTask newTask = new TodoTask(m.group(1));
      tasks.add(newTask);
    } else {
      throw new JoeException("Todo Task file is corrupt");
    }
  }

  private void handleDeadline(String input, ArrayList<Task> tasks) throws JoeException {
    Matcher m = deadlinePattern.matcher(input);
    if (m.find()) {
      TodoTask newTask = new TodoTask(m.group(1));
      tasks.add(newTask);
    } else {
      throw new JoeException("Deadline Task file is corrupt");
    }
  }

  private void handleEvent(String input, ArrayList<Task> tasks) throws JoeException {
    Matcher m = eventPattern.matcher(input);
    if (m.find()) {
      TodoTask newTask = new TodoTask(m.group(1));
      tasks.add(newTask);
    } else {
      throw new JoeException("Event Task file is corrupt");
    }
  }

  public ArrayList<Task> readTasks() throws JoeException, IOException {
    ArrayList<Task> tasks = new ArrayList<>();
    if (!Files.exists(taskFilePath)) {
      throw new FileNotFoundException();
    }
    List<String> lines = Files.readAllLines(taskFilePath);
    for (String line : lines) {
      Matcher m = taskPattern.matcher(line);
      if (!m.find()) {
        throw new JoeException("Task file is corrupt");
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
        default:
          throw new JoeException("Task file is corrupt");
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
