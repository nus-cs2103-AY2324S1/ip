import java.util.Scanner;
public class Duke {
    private static final String botName = "cc";

    private static final ToDo toDo = new ToDo();

    private static void greet() {
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
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
                toDo.print();
            } else if (isMarkCommand(input)) {
                int pos = extractValue(input);
                if (pos > toDo.size() || pos <= 0) {
                    System.out.println("Invalid index. Please enter again.");
                    continue;
                }
                toDo.mark(pos);
            } else if (isUnmarkCommand(input)) {
                int pos = extractValue(input);
                if (pos > toDo.size() || pos <= 0) {
                    System.out.println("Invalid index. Please enter again.");
                    continue;
                }
                toDo.unmark(pos);
            } else {
                toDo.add(new Task(input));
            }
        }
    }
}
