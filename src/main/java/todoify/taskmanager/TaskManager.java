package todoify.taskmanager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import todoify.storage.InternalPath;
import todoify.storage.InternalStorage;
import todoify.taskmanager.task.Deadline;
import todoify.taskmanager.task.Event;
import todoify.taskmanager.task.Task;
import todoify.taskmanager.task.Todo;

/**
 * A class that manages a list of items aka "tasks".
 */
public class TaskManager {

    /**
     * The default filename used to read and write tasks to.
     */
    private static final String DEFAULT_FILENAME = "tasks.json";

    private List<Task> taskList;
    private final InternalPath storageLocation;
    private final InternalStorage storageHandler;

    /**
     * Constructs a task manager, managing a list of items representing "tasks", with a custom storage location and
     * storage handler.
     *
     * @param storageLocation The storage location.
     * @param storageHandler  The handler for processing storage operations.
     */
    public TaskManager(InternalPath storageLocation, InternalStorage storageHandler) {
        this.taskList = new ArrayList<>();
        this.storageLocation = storageLocation;
        this.storageHandler = storageHandler == null ? InternalStorage.getDefault() : storageHandler;
    }

    /**
     * Constructs a task manager, managing a list of items representing "tasks", with a custom storage location and
     * default storage handler.
     *
     * @param storageLocation Path
     */
    public TaskManager(InternalPath storageLocation) {
        this(storageLocation, null);
    }

    /**
     * Constructor for a task manager, managing a list of items representing "tasks", with the default storage location
     * and default storage handler.
     */
    public TaskManager() {
        this(InternalPath.of(DEFAULT_FILENAME));
    }

    /**
     * Obtains the currently stored tasks as an immutable list.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return Collections.unmodifiableList(this.taskList);
    }

    /**
     * Queries the number of tasks stored.
     *
     * @return The number of tasks currently stored as an integer.
     */
    public int getTaskCount() {
        return this.taskList.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     * @return `true`, by {@link List#add} definition.
     */
    public boolean addTask(Task task) {
        return this.taskList.add(task);
    }

    /**
     * Gets a task from the task list with the corresponding index.
     *
     * @param index The index to obtain.
     * @return The task in question at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Removes the given task from the task list.
     *
     * @param task The task to remove.
     * @return `true` if the task exists in the list and is successfully removed, `false` otherwise.
     */
    public boolean removeTask(Task task) {
        return this.taskList.remove(task);
    }

    /**
     * Removes the task at the corresponding index.
     *
     * @param index The index to remove.
     * @return The task in question that was removed.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public Task removeTask(int index) {
        return this.taskList.remove(index);
    }

    /**
     * Loads and replaces the task list in memory with the one currently in storage.
     *
     * <p>
     * This method will load the data from storage and replace all in-memory contents. Any unrecognized, incompatible
     * tasks may be omitted entirely.
     * </p>
     *
     * @throws IOException if there were any issues retrieving the data.
     */
    public void loadFromStorage() throws IOException, JsonSyntaxException {
        Gson gson = new Gson();
        try {
            String data = this.storageHandler.loadFrom(this.storageLocation);
            JsonArray array = JsonParser.parseString(data).getAsJsonArray();

            // Prepare a new list of tasks.
            List<Task> tasks = new ArrayList<>();

            // Prepare a new set of classes, from most specific to least specific.
            // This ordering is required to match the provided JSON to a class that's as specific as possible.
            @SuppressWarnings("unchecked")
            Class<Task>[] availClasses = new Class[] { Event.class, Deadline.class, Todo.class };

            // Iterate through the items in the JSON array.
            for (JsonElement item : array) {
                Task task = null;

                // Iterate through possible classes and attempt to get them.
                for (Class<Task> cls : availClasses) {
                    try {
                        task = Task.fromJsonRepresentation(item, cls);
                    } catch (JsonSyntaxException | IllegalArgumentException e) {
                        // This is not the correct class.
                    }

                    if (task != null) {
                        break;
                    }
                }

                // Skip if we cannot parse.
                if (task == null) {
                    continue;
                }

                // Add it if we can.
                tasks.add(task);
            }

            // Replace the task list with a new one
            this.taskList = tasks;

        } catch (JsonSyntaxException | FileNotFoundException e) {

            // Silence these errors and replace the task list with a new one.
            this.taskList = new ArrayList<>();

        }
    }

    /**
     * Saves and replaces the task list in storage with the one currently in memory.
     *
     * @throws IOException if there were any issues saving the data.
     */
    public void saveToStorage() throws IOException {
        Gson gson = new Gson();
        String data = gson.toJson(this.taskList);
        this.storageHandler.saveTo(this.storageLocation, data);
    }
}
