package storage;

import adapters.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;
import utility.DateUtility;

import java.io.*;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import static java.lang.Boolean.parseBoolean;

public class FileStorage implements IStorage {
  private static final String DATA_FILE_PATH = "data/data.json";
  private static final Gson gson =
      new GsonBuilder()
          .excludeFieldsWithModifiers(Modifier.TRANSIENT)
          .setPrettyPrinting()
          .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
          .create();

  @Override
  public List<Task> load() {
    try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE_PATH))) {
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
            LocalDate deadlineDate = DateUtility.parse(entry.get("due"));
            if (deadlineDate == null) {
              throw new IllegalStateException("Invalid deadline format");
            }
            task = new Deadline(entry.get("name"), deadlineDate);
            break;
          case "event":
            LocalDate fromDate = DateUtility.parse(entry.get("from"));
            LocalDate toDate = DateUtility.parse(entry.get("to"));
            if (fromDate == null || toDate == null) {
              throw new IllegalStateException("Invalid from/to format");
            }
            task = new Event(entry.get("name"), fromDate, toDate);
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
    } catch (IOException e) {
      System.out.println("Failed to read tasks from data file");
      System.exit(0);
    }

    return new ArrayList<>();
  }

  @Override
  public void save(List<Task> tasks) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE_PATH))) {
      gson.toJson(tasks, bw);
    } catch (IOException e) {
      System.out.println("Failed to save tasks to data file");
      System.exit(0);
    }
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
