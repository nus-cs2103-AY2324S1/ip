package linus.storage;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import linus.task.Task;

/**
 * Represents a TaskDeserializer object that implements JsonDeserializer.
 *
 * @see <a href="https://www.baeldung.com/gson-list">GSON List</a>
 */
public class TaskDeserializer implements JsonDeserializer<Task> {
    private String taskTypeElementName;
    private Gson gson;
    private Map<String, Class<? extends Task>> taskTypeRegistry;

    /**
     * Constructs a TaskDeserializer object with the specified task type element name.
     *
     * @param taskTypeElementName The task type element name.
     */
    public TaskDeserializer(String taskTypeElementName) {
        this.taskTypeElementName = taskTypeElementName;
        this.gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).create();
        this.taskTypeRegistry = new HashMap<>();
    }

    /**
     * Registers a task type with the specified task type name and task type.
     *
     * @param taskTypeName The task type name.
     * @param taskType     The task type.
     */
    public void registerTaskType(String taskTypeName, Class<? extends Task> taskType) {
        taskTypeRegistry.put(taskTypeName, taskType);
    }

    /**
     * Deserializes the specified Json element into a Task object.
     *
     * @param json    The Json element.
     * @param typeOfT The type of the Object to deserialize to.
     * @param context
     * @return The deserialized Task object.
     */
    public Task deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonObject taskObject = json.getAsJsonObject();
        JsonElement taskTypeElement = taskObject.get(taskTypeElementName);

        Class<? extends Task> taskType = taskTypeRegistry.get(taskTypeElement.getAsString());
        return gson.fromJson(taskObject, taskType);
    }
}
