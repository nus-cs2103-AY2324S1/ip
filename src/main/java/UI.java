import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UI {
    private String name;
    private static final String DATETIME_INPUT_FORMAT = "yyyy-MM-dd HHmm";
    public static final DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);

    public UI(String name) {
        this.name = name;
    }

    public void handleCommand(ArrayList<String> parsedInput, ArrayList<Task> taskList) {}

    private void printWelcomeMessage() {
        String name = "Derek";
        System.out.println("Hello! I'm " + this.name);
        System.out.println("What can I do for you?");
    }

    private void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
