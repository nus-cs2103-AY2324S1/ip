package storage;

import com.google.gson.*;
import duke.Duke;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The `LocalDateAdapter` class is a Gson adapter for serializing and deserializing `LocalDate` objects.
 */
public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
    /**
     * The date-time formatter used for formatting and parsing dates.
     */
    private static final DateTimeFormatter DATE_FORMATTER = Duke.DATETIME_FORMATTER;

    /**
     * Deserialize a `LocalDate` object from a JSON element.
     *
     * @param json       The JSON element containing the date as a string.
     * @param typeOfT    The type of the object to deserialize.
     * @param context    The deserialization context.
     * @return A `LocalDate` object deserialized from the JSON element.
     * @throws JsonParseException If there is an issue with JSON parsing.
     */
    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String dateStr = json.getAsString();
        return LocalDate.parse(dateStr, DATE_FORMATTER);
    }

    /**
     * Serialize a `LocalDate` object to a JSON element.
     *
     * @param src        The `LocalDate` object to serialize.
     * @param typeOfSrc  The type of the source object.
     * @param context    The serialization context.
     * @return A JSON element representing the serialized `LocalDate` object.
     */
    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(DATE_FORMATTER.format(src));
    }
}
