package cyrus.storage;

import static java.lang.Boolean.parseBoolean;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cyrus.adapters.LocalDateAdapter;
import cyrus.tasks.Deadline;
import cyrus.tasks.Event;
import cyrus.tasks.Task;
import cyrus.tasks.ToDo;
import cyrus.utility.DateUtility;

/**
 * Storage of task list using JSON files.
 */
public class FileStorage implements IStorage {
    private static final Gson GSON =
            new GsonBuilder()
                    .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                    .setPrettyPrinting()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();
    private final String dataFilePath;

    /**
     * Constructs {@code FileStorage} instance given file path to read and write to.
     *
     * @param dataFilePath file path to read/write data to/from.
     */
    public FileStorage(String dataFilePath) {
        assert !dataFilePath.trim().equals("") : "Data file path cannot be empty";
        this.dataFilePath = dataFilePath;
    }

    /**
     * Loads a list of {@code Task} from a file, determined by {@code dataFilePath}.
     *
     * @return list of {@code Task} from file.
     * @throws AssertionError if task format is invalid.
     */
    // TODO: Figure out a better way to handle file IO errors in the code
    // Potentially just delete and re-create a blank file (but that means any existing data is
    // immediately lost)
    @Override
    public List<Task> load() {
        try (BufferedReader br = new BufferedReader(new FileReader(dataFilePath))) {
            Type listType = new TypeToken<List<HashMap<String, String>>>() {
            }.getType();
            List<HashMap<String, String>> jsonTasks = GSON.fromJson(br, listType);
            List<Task> fileTasks = new ArrayList<>();

            if (jsonTasks == null) {
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
            System.out.println("Failed to read cyrus.tasks from data file");
            System.exit(0);
        }

        return new ArrayList<>();
    }

    /**
     * Saves a list of tasks into a JSON file.
     *
     * @param tasks list of tasks to save
     */
    @Override
    public void save(List<Task> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFilePath))) {
            GSON.toJson(tasks, bw);
        } catch (IOException e) {
            System.out.println("Failed to save cyrus.tasks to data file");
            System.exit(0);
        }
    }


    private void createDataFile() {
        File file = new File(dataFilePath);
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException ne) {
            System.out.println("Unable to create file, Cyrus cannot run");
            System.exit(0);
        }
    }

    private void enforceFields(HashMap<String, String> map) {
        String[] mandatoryKeys = {"type", "status", "name"};
        Consumer<String[]> checkKeys = (keys) -> {
            for (String key : keys) {
                assert map.containsKey(key) : String.format("All entries in data.json must contain \"%s\" field", key);
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
        default:
            break;
        }

    }
}
