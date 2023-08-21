import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Duke {

    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String CHATBOT_NAME = "Koko";
    private static ArrayList<String> tasks = new ArrayList<>();

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

    private static void parseInput(String input) {
        switch (input) {
            case "list":
                String result = IntStream.range(0, tasks.size())
                        .mapToObj(i -> (i + 1) + ". " + tasks.get(i))
                        .collect(Collectors.joining("\n"));
                Duke.printFormatted(result);
                break;
            default:
                tasks.add(input);
                Duke.printFormatted("added: " + input);
        }
    }

    public static void main(String[] args) {
        Duke.greet();

        Scanner scanner = new Scanner(System.in);
        Stream.generate(scanner::nextLine)
                .takeWhile(input -> !input.equals("bye"))
                .forEach(Duke::parseInput);

        Duke.exit();
    }
}
