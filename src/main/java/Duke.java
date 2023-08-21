import java.util.Arrays;
import java.util.stream.Collectors;

public class Duke {

    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String CHATBOT_NAME = "Koko";

    private static void printFormatted(String originalMessage) {
        String indentedMessage = Arrays.stream(originalMessage.split("\n"))
                .map(line -> "     " + line)
                .collect(Collectors.joining("\n"));

        String formattedMessage = String.format("    %s\n%s\n    %s",
                HORIZONTAL_LINE, indentedMessage, HORIZONTAL_LINE);

        System.out.println(formattedMessage);
    }

    private static void greet() {
        Duke.printFormatted("Hello! I'm " + CHATBOT_NAME + "\nWhat can I do for you?");
    }

    private static void exit() {
        Duke.printFormatted("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Duke.greet();
        Duke.exit();
    }
}
