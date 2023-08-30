package cyrus.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import cyrus.utility.DateUtility;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {
    @Override
    public void write(JsonWriter jsonWriter, LocalDate localDate) throws IOException {
        jsonWriter.value(DateUtility.toInputFormat(localDate));
    }

    @Override
    public LocalDate read(JsonReader jsonReader) throws IOException {
        String input = jsonReader.nextString();
        return DateUtility.parse(input);
    }
}
