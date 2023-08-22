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
            String command = sc.next();

            switch (command) {
                case "bye":
                    farewell();
                    shouldTerminate = true;
                    break;
                case "list":
                    list();
                    break;
                case "mark":
                    mark(sc.nextInt());
                    break;
                case "unmark":
                    unmark(sc.nextInt());
                    break;
                default:
                    add(command);
            }
        }
    }
}
