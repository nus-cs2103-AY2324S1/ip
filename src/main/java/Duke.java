import java.util.Scanner;

public class Duke {

    private static final String DIVIDER = "____________________________________________________________";
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";

    public static void main(String[] args) {
        Duke.greet();

        Scanner scanner = new Scanner(System.in);
        TaskList list = new TaskList();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals(Duke.EXIT_COMMAND)) {
                break;
            } else if (input.equals(Duke.LIST_COMMAND)) {
                list.listTasks();
            } else {
                list.addTask(input);
            }
        }

        Duke.exit();
        scanner.close();
    }

    public static void greet() {
        System.out.println(DIVIDER);
        System.out.println(" Hello! I'm LilBro!");
        System.out.println(" What can I do for you?");
        System.out.println(DIVIDER);
    }

    public static void exit() {
        System.out.println(DIVIDER);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    public static void echo(String input) {
        System.out.println(DIVIDER);
        System.out.println(input);
        System.out.println(DIVIDER);
    }
}
