package cyrus.adapters;

import java.io.IOException;
import java.time.LocalDate;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import cyrus.utility.DateUtility;

/**
 * GSON adapter to properly handle {@code LocalDate} values in the various {@code Task}.
 */
public class LocalDateAdapter extends TypeAdapter<LocalDate> {
    /**
     * When writing {@code LocalDateAdapter} to JSON, convert it to the input format of {@code dd
     * /MM/yyy}.
     *
     * @param jsonWriter writes the value to the JSON
     * @param localDate  the Java object to write. May be null.
     * @throws IOException if {@code jsonWriter} encounters an error with writing
     */
    @Override
    public void write(JsonWriter jsonWriter, LocalDate localDate) throws IOException {
        jsonWriter.value(DateUtility.toInputFormat(localDate));
    }

    /**
     * When attempting to deserialize to a {@code LocalDate}, use the same input format as {@code
     * dd/MM/yyyy}.
     *
     * @param jsonReader reads the value from JSON
     * @return {@code LocalDate} value
     * @throws IOException if {@code jsonReader} encounters error with reading
     */
    @Override
    public LocalDate read(JsonReader jsonReader) throws IOException {
        String input = jsonReader.nextString();
        return DateUtility.parse(input);
    }
}
