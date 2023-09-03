package Storage;

import Tasks.Task;
import com.google.gson.*;

import java.lang.reflect.Type;

public class TaskAdapter implements JsonDeserializer<Task>, JsonSerializer<Task> {
    @Override
    public JsonElement serialize(Task src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", src.getClass().getName());
        jsonObject.add("data", context.serialize(src));
        return jsonObject;
    }

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
