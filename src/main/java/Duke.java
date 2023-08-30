import java.util.Scanner;
public class Duke {
    private static final String botName = "cc";

    private static final TaskParser parser = new TaskParser();

    private static final Memory memory = new Memory();

    private static void greet() {
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");
    }

    private static void bye() {
        System.out.print("Bye. Hope to see you again soon!");
    }

    private static void echo(String input) {
        System.out.println(input);
    }

    private static boolean isMarkCommand(String input) {
        return input.startsWith("mark ");
    }

    private static boolean isUnmarkCommand(String input) {
        return input.startsWith("unmark ");
    }

    private static boolean isDeleteCommand(String input) {
        return input.startsWith("delete ");
    }

    private static int extractValue(String input) {
        String[] parts = input.split("\\s+");
        return Integer.parseInt(parts[1]);
    }

    public static void main(String[] args) {
        Duke.greet();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                Duke.bye();
                running = false;
            } else if (input.equals("list")) {
                memory.print();
            } else if (isMarkCommand(input)) {
                int pos = extractValue(input);
                if (pos > memory.size() || pos <= 0) {
                    System.out.println("Invalid index. Please enter again.");
                    continue;
                }
                memory.mark(pos);
            } else if (isUnmarkCommand(input)) {
                int pos = extractValue(input);
                if (pos > memory.size() || pos <= 0) {
                    System.out.println("Invalid index. Please enter again.");
                    continue;
                }
                memory.unmark(pos);
            } else if (isDeleteCommand(input)) {
                int pos = extractValue(input);
                if (pos > memory.size() || pos <= 0) {
                    System.out.println("Invalid index. Please enter again.");
                    continue;
                }
                memory.delete(pos);
            } else {
                memory.add(parser.parseTask(input));
            }
        }
    }
}
