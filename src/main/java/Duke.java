import java.util.Scanner;

public class Duke {
    private static final String NAME = "Jimmy";
    private static final int MAX_ENTRIES = 100;
    private static final Task[] tasks = new Task[MAX_ENTRIES];
    private static int cursor = 0;

    private static void print(String... strings) {
        for (String s : strings) {
            System.out.print('\t');
            System.out.println(s);
        }
    }

    private static void greet() {
        print(String.format("Hello! I'm %s", NAME), "What can I do for you?");
    }

    private static void farewell() {
        print("Bye. Hope to see you again soon!");
    }

    private static Command toCommand(String input) {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    private static void list() {
        print("Here are the tasks in your list:");
        for (int i = 0; i < cursor; i++) {
            String taskEntry = String.format("%d. %s", i + 1, tasks[i]);
            print(taskEntry);
        }
    }

    private static void add(Task task) {
        tasks[cursor++] = task;
        print(
                "Got it. I've added this task:",
                task.toString(),
                String.format("Now you have %d tasks in the list.", cursor)
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
        print("Nice! I've marked this task as done:", task.toString());
    }

    private static void unmark(int index) {
        Task task = tasks[index - 1];
        task.markAsUndone();
        print("OK, I've marked this task as not done yet:", task.toString());
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
                    print("Unknown command!");
            }
        }
    }

    enum Command {
        BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, UNKNOWN
    }
}
