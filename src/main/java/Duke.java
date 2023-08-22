import java.util.Scanner;

public class Duke {
    private static final String NAME = "Jimmy";
    private static final int MAX_ENTRIES = 100;
    private static final String[] tasks = new String[MAX_ENTRIES];
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
        for (int i = 0; i < cursor; i++) {
            String taskEntry = String.format("%d. %s", i + 1, tasks[i]);
            print(taskEntry);
        }
    }

    public static void main(String[] args) {
        greet();

        boolean shouldTerminate = false;
        Scanner sc = new Scanner(System.in);

        while (!shouldTerminate && sc.hasNextLine()) {
            String command = sc.nextLine();

            switch (command) {
                case "bye":
                    farewell();
                    shouldTerminate = true;
                    break;
                case "list":
                    list();
                    break;
                default:
                    tasks[cursor++] = command;
                    print(String.format("added: %s", command));
            }
        }
    }
}
