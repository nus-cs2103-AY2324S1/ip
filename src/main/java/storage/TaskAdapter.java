package storage;

import tasks.Task;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * The `TaskAdapter` class is a Gson adapter for serializing and deserializing `Task` objects.
 */
public class TaskAdapter implements JsonDeserializer<Task>, JsonSerializer<Task> {
    /**
     * Serialize a `Task` object to a JSON element.
     *
     * @param src       The `Task` object to serialize.
     * @param typeOfSrc The type of the source object.
     * @param context   The serialization context.
     * @return A JSON element representing the serialized `Task` object.
     */
    @Override
    public JsonElement serialize(Task src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", src.getClass().getName());
        jsonObject.add("data", context.serialize(src));
        return jsonObject;
    }

    /**
     * Deserialize a `Task` object from a JSON element.
     *
     * @param json     The JSON element containing the serialized `Task` object.
     * @param typeOfT  The type of the object to deserialize.
     * @param context  The deserialization context.
     * @return A `Task` object deserialized from the JSON element.
     * @throws JsonParseException If there is an issue with JSON parsing.
     */
    @Override
    public Task deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        JsonElement data = jsonObject.get("data");
        try {
            Class<?> clazz = Class.forName(type);
            return context.deserialize(data, clazz);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }
}
