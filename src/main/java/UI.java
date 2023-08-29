import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UI {
    private static final String DATETIME_INPUT_FORMAT = "yyyy-MM-dd HHmm";
    public static final DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);

    public UI() {}

    public void handleCommand(ArrayList<String> parsedInput, ArrayList<Task> taskList) {}

    private static void printWelcomeMessage() {}

    private static void printGoodbyeMessage() {}
}
