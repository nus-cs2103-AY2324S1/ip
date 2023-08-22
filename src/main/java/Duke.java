import java.util.Scanner;

public class Duke {
    private static final String NAME = "Jimmy";
    private static final int MAX_ENTRIES = 100;
    private static final Task[] tasks = new Task[MAX_ENTRIES];
    private static int cursor = 0;

    enum Command {
        BYE,
        LIST,
        ADD,
        MARK,
        UNMARK
    }

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
            return Command.ADD;
        }
    }

    private static void list() {
        print("Here are the tasks in your list:");
        for (int i = 0; i < cursor; i++) {
            String taskEntry = String.format("%d. %s", i + 1, tasks[i]);
            print(taskEntry);
        }
    }

    private static void add(String description) {
        tasks[cursor++] = new Task(description);
        print(String.format("Added: %s", description));
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

            switch (command) {
                case BYE:
                    farewell();
                    shouldTerminate = true;
                    break;
                case LIST:
                    list();
                    break;
                case MARK:
                    mark(Integer.parseInt(tokens[1]));
                    break;
                case UNMARK:
                    unmark(Integer.parseInt(tokens[1]));
                    break;
                default:
                    add(input);
            }
        }
    }
}
