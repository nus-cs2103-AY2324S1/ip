import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Duke {

    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String CHATBOT_NAME = "Koko";
    private static ArrayList<Task> tasks = new ArrayList<>();

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

    private static void printTaskAddedMessage(Task task) {
        Duke.printFormatted(String.format("Got it. I've added this task:\n  %s\n Now you have %d %s in the list.",
                task.toString(), Duke.tasks.size(), (Duke.tasks.size() == 1 ? "task" : "tasks")));
    }

    private static void parseInput(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String remaining = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "list":
                String result = IntStream.range(0, tasks.size())
                        .mapToObj(i -> (i + 1) + ". " + tasks.get(i))
                        .collect(Collectors.joining("\n"));
                Duke.printFormatted(result);
                break;
            case "mark":
                Task markTarget = tasks.get(Integer.parseInt(remaining) - 1);
                markTarget.markAsDone();
                Duke.printFormatted("Nice! I've marked this task as done:\n" + markTarget.toString());
                break;
            case "unmark":
                Task unmarkTarget = tasks.get(Integer.parseInt(remaining) - 1);
                unmarkTarget.markAsUndone();
                Duke.printFormatted("OK! I've marked this task as not done yet:\n" + unmarkTarget.toString());
                break;
            default:
                tasks.add(new Task(input));
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
