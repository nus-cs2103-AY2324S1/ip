import java.time.format.DateTimeFormatter;

public class DukeEnvironmentConstants {
    public static enum taskType {
        EVENT,
        DEADLINE,
        TODO
    }
    public static String FILE_PATH = "./data/duke.txt";
    public static String DATA_FOLDER = "./data/";
    public static DateTimeFormatter FORMATTER1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    public static DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm'hrs'");    
}
