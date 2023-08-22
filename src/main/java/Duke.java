import java.util.Scanner;

public class Duke {
    private static final int TAB_WIDTH = 4;
    private static final String NAME = "Jimmy";
    private static final int MAX_ENTRIES = 100;
    private static final Task[] tasks = new Task[MAX_ENTRIES];
    private static int cursor = 0;

    private static void say(String... strings) {
        for (String s : strings) {
            for (int i = 0; i < TAB_WIDTH; i++) {
                System.out.print(" ");
            }
            System.out.println(s);
        }
    }

    private static void greet() {
        say(String.format("Hello! I'm %s", NAME), "What can I do for you?");
    }

    private static void farewell() {
        say("Bye. Hope to see you again soon!");
    }

    private static Command toCommand(String input) {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    private static void list() {
        say("Here are the tasks in your list:");
        for (int i = 0; i < cursor; i++) {
            String taskEntry = String.format("%d. %s", i + 1, tasks[i]);
            say(taskEntry);
        }
    }

    private static void add(Task task) {
        tasks[cursor++] = task;
        say(
                "Got it. I've added this task:",
                task.toString(),
                String.format("Now you have %d %s in the list.", cursor, cursor == 1 ? "task" : "tasks")
        );
    }

    private static void addTodo(String details) {
        Todo todo = new Todo(details);
        add(todo);
    }

    private static void addDeadline(String details) {
        String[] tokens = details.split(" /by ");
        String description = tokens[0];
        String by = tokens[1];

        Deadline deadline = new Deadline(description, by);
        add(deadline);
    }

    private static void addEvent(String details) {
        String[] tokens = details.split(" /from ");
        String description = tokens[0];
        tokens = tokens[1].split(" /to ");
        String from = tokens[0];
        String to = tokens[1];

        Event event = new Event(description, from, to);
        add(event);
    }

    private static void mark(int index) {
        Task task = tasks[index - 1];
        task.markAsDone();
        say("Nice! I've marked this task as done:", task.toString());
    }

    private static void unmark(int index) {
        Task task = tasks[index - 1];
        task.markAsUndone();
        say("OK, I've marked this task as not done yet:", task.toString());
    }

    public static void main(String[] args) {
        greet();

        boolean shouldTerminate = false;
        Scanner sc = new Scanner(System.in);

        while (!shouldTerminate && sc.hasNextLine()) {
            String input = sc.nextLine().trim();
            String[] tokens = input.split(" ", 2);

            Command command = toCommand(tokens[0]);
            String commandArgs = tokens.length > 1 ? tokens[1] : "";

            switch (command) {
                case BYE:
                    farewell();
                    shouldTerminate = true;
                    break;
                case LIST:
                    list();
                    break;
                case TODO:
                    addTodo(commandArgs);
                    break;
                case DEADLINE:
                    addDeadline(commandArgs);
                    break;
                case EVENT:
                    addEvent(commandArgs);
                    break;
                case MARK:
                    mark(Integer.parseInt(commandArgs));
                    break;
                case UNMARK:
                    unmark(Integer.parseInt(commandArgs));
                    break;
                default: // UNKNOWN
                    say("Unknown command!");
            }
        }
    }

    enum Command {
        BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, UNKNOWN
    }
}
