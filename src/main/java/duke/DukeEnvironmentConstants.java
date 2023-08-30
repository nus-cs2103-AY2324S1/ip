package duke;
import java.time.format.DateTimeFormatter;

public abstract class DukeEnvironmentConstants {
    public static final String GREETINGS = "Hello! I'm AChatBot\n"
            + "What can I do for you?";
    public static enum TaskType {
        EVENT,
        DEADLINE,
        TODO
    }
    public static final String FILE_PATH = "./data/duke.txt";
    public static final String DATA_FOLDER = "./data/";
    public static final DateTimeFormatter FORMATTER1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    public static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm'hrs'");
}
