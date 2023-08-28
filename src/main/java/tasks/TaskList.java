package tasks;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Boolean.parseBoolean;

public class TaskList {
  private static TaskList INSTANCE;
  private final List<Task> tasks = new ArrayList<>();
  private static final String DATA_FILE_PATH = "data/data.json";
  private static final Gson gson = new Gson();

  public TaskList() {
    List<Task> existingTasks = loadTasks();
    if (existingTasks.size() > 0) {
      this.tasks.addAll(existingTasks);
    }
  }

  public static TaskList getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new TaskList();
    }

    return INSTANCE;
  }

  public void addTask(Task task) {
    this.tasks.add(task);
    this.saveTasks();
  }

  public void removeTask(int index) {
    this.checkIndex(index);
    this.tasks.remove(index);
    this.saveTasks();
  }

  public void setTaskStatus(int index, boolean status) {
    this.checkIndex(index);
    this.tasks.get(index).setDone(status);
    this.saveTasks();
  }

  public int size() {
    return this.tasks.size();
  }

  public Task getTask(int index) {
    this.checkIndex(index);
    return this.tasks.get(index);
  }

  private void checkIndex(int index) {
    if (index < 0 || index >= this.tasks.size()) {
      throw new IllegalArgumentException("Invalid index provided");
    }
  }

  @Override
  public String toString() {
    if (this.tasks.size() == 0) {
      return "You do not have any tasks, use todo, deadline, or event to add new ones!";
    }
    List<String> formatted = IntStream
        .range(0, tasks.size())
        .mapToObj((j) -> String.format("%d. %s", j + 1, this.tasks.get(j)))
        .collect(Collectors.toList());
    formatted.add(0, "Here are the tasks in your list:");
    return String.join("\n", formatted);
  }

  private void saveTasks() {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE_PATH))) {
      List<HashMap<String, String>> toSave = new ArrayList<>();
      for (Task task : this.tasks) {
        // Manually destructure the entry since we are including the name of the task as well
        HashMap<String, String> entry = new HashMap<>();
        entry.put("type", "todo");
        entry.put("name", task.getName());
        entry.put("status", String.valueOf(task.getDone()));
        if (task instanceof Deadline) {
          entry.put("due", ((Deadline) task).getDeadline());
        } else if (task instanceof Event) {
          entry.put("from", ((Event) task).getFrom());
          entry.put("to", ((Event) task).getTo());
        } else if (!(task instanceof ToDo)) {
          throw new IllegalStateException("Invalid task found, this should not happen");
        }
        toSave.add(entry);
      }
      gson.toJson(toSave, bw);
    } catch (IOException e) {
      System.out.println("Failed to save tasks to data file");
      System.exit(0);
    }
  }

  private static List<Task> loadTasks() {
    try {
      BufferedReader br = new BufferedReader(new FileReader(DATA_FILE_PATH));
      Type listType = new TypeToken<List<HashMap<String, String>>>() {
      }.getType();
      List<HashMap<String, String>> jsonTasks = gson.fromJson(br, listType);
      List<Task> fileTasks = new ArrayList<>();

      if (jsonTasks == null) {
        // Means the data file is empty
        return new ArrayList<>();
      }

      for (HashMap<String, String> entry : jsonTasks) {
        enforceFields(entry);
        String type = entry.get("type");
        Task task;
        switch (type) {
          case "todo":
            task = new ToDo(entry.get("name"));
            break;
          case "deadline":
            task = new Deadline(entry.get("name"), entry.get("due"));
            break;
          case "event":
            task = new Event(entry.get("name"), entry.get("from"), entry.get("to"));
            break;
          default:
            throw new IllegalStateException("Invalid task type found in data.json");
        }
        task.setDone(parseBoolean(entry.get("status")));
        fileTasks.add(task);
      }
      return fileTasks;
    } catch (FileNotFoundException e) {
      createDataFile();
    }

    return new ArrayList<>();
  }

  private static void createDataFile() {
    File file = new File(DATA_FILE_PATH);
    try {
      file.getParentFile().mkdirs();
      file.createNewFile();
    } catch (IOException ne) {
      System.out.println("Unable to create file, Cyrus cannot run");
      System.exit(0);
    }
  }

  private static void enforceFields(HashMap<String, String> map) {
    String[] mandatoryKeys = {"type", "status", "name"};
    Consumer<String[]> checkKeys = (keys) -> {
      for (String key : keys) {
        if (!map.containsKey(key)) {
          throw new IllegalStateException(
              String.format("All entries in data.json must contain \"%s\" field", key)
          );
        }
      }
    };
    checkKeys.accept(mandatoryKeys);

    String type = map.get("type");
    switch (type) {
      case "deadline":
        checkKeys.accept(new String[]{"due"});
        break;
      case "event":
        checkKeys.accept(new String[]{"from", "to"});
        break;
    }
  }
}
