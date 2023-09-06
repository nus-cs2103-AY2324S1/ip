import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class ToDo extends Task {
    public static final String TYPE = "todo";

    public ToDo(String description) {
        super(description);
    }

    public ToDo(Boolean isDone, String description) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getEncodedString() {
        String encoded = "T | ";

        if (super.isDone) {
            encoded += "1";
        } else {
            encoded += "0";
        }

        encoded += " | " + super.getDescription();

        return encoded;
    }
}
