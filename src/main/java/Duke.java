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
        try {
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String remaining = parts.length > 1 ? parts[1] : "";

            if (remaining.isEmpty()) {
                switch (command) {
                    case "mark":
                    case "unmark":
                        throw new DukeException("Please specify a task number.");
                    case "todo":
                        throw new DukeException("Description for todo cannot be empty.");
                    case "deadline":
                        throw new DukeException("Description and date for deadline cannot be empty.");
                    case "event":
                        throw new DukeException("Description, start date, and end date for event cannot be empty.");
                }
            }

            switch (command) {
                case "list":
                    String result = IntStream.range(0, tasks.size())
                            .mapToObj(i -> (i + 1) + ". " + tasks.get(i))
                            .collect(Collectors.joining("\n"));
                    Duke.printFormatted(result);
                    break;
                case "mark":
                    int markIndex = Integer.parseInt(remaining) - 1;
                    if (markIndex < 0 || markIndex >= tasks.size()) {
                        throw new DukeException("Invalid task number.");
                    }
                    Task markTarget = tasks.get(markIndex);
                    markTarget.markAsDone();
                    Duke.printFormatted("Nice! I've marked this task as done:\n" + markTarget.toString());
                    break;
                case "unmark":
                    int unmarkIndex = Integer.parseInt(remaining) - 1;
                    if (unmarkIndex < 0 || unmarkIndex >= tasks.size()) {
                        throw new DukeException("Invalid task number.");
                    }
                    Task unmarkTarget = tasks.get(unmarkIndex);
                    unmarkTarget.markAsUndone();
                    Duke.printFormatted("OK! I've marked this task as not done yet:\n" + unmarkTarget.toString());
                    break;
                case "todo":
                    Todo newTodo = new Todo(remaining);
                    tasks.add(newTodo);
                    printTaskAddedMessage(newTodo);
                    break;
                case "deadline":
                    String[] deadlineParts = remaining.split("/by ", 2);
                    if (deadlineParts.length < 2) {
                        throw new DukeException("Missing '/by' or date for deadline.");
                    }
                    Deadline newDeadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                    tasks.add(newDeadline);
                    printTaskAddedMessage(newDeadline);
                    break;
                case "event":
                    String[] splitByTo = remaining.split("/to ", 2);
                    if (splitByTo.length < 2) {
                        throw new DukeException("Missing '/to' or end date for event.");
                    }
                    String[] splitByFrom = splitByTo[0].split("/from ", 2);
                    if (splitByFrom.length < 2) {
                        throw new DukeException("Missing '/from' or start date for event.");
                    }
                    Event newEvent = new Event(splitByFrom[0], splitByFrom[1], splitByTo[1]);
                    tasks.add(newEvent);
                    printTaskAddedMessage(newEvent);
                    break;
                default:
                    throw new DukeException("Each message should start with one of the following commands: list, mark, unmark, todo, deadline, event");
            }

        } catch (NumberFormatException e) {
            Duke.printFormatted("Please enter a valid task number.");
        } catch (DukeException e) {
            Duke.printFormatted(e.getMessage());
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
