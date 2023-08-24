import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        hello_world();
        converse();
        goodbye_world();
    }

    public static void hello_world() {
        System.out.println("Hello! I'm");
        printName();
        System.out.println("What can I do for you?");
        print_divider_line();
    }

    public static void converse() {
        final String END_COMMAND = "bye";
        final String LIST_COMMAND = "list";
        final String MARK_COMMAND = "mark";
        final String UNMARK_COMMAND = "unmark";

        Task[] taskList = new Task[100];
        int size = 0;

        Scanner scanner = new Scanner(System.in);

        String message;
        boolean talk = true;
        while (talk) {
            System.out.print("Message:");
            message = scanner.nextLine();

            String[] args = getArgs(message);
            switch (args[0]) {
                case END_COMMAND:
                    talk = false;
                    break;
                case LIST_COMMAND:
                    print_all_task(taskList, size);
                    break;
                case MARK_COMMAND:
                    mark_task_done(taskList, args[1]);
                    break;
                case UNMARK_COMMAND:
                    unmark_task_undone(taskList, args[1]);
                    break;
                default:
                    taskList[size++] = new Task(message);
                    System.out.println("Written to task list:" + message);
            }

            print_divider_line();
        }
    }

    public static String[] getArgs(String text) {
        return text.split("\\s+");
    }

    public static void mark_task_done(Task[] taskList, String text) {
        Task task = taskList[Integer.parseInt(text) - 1];
        task.markDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("     " + task.getDescriptionWithCheckbox());
    }

    public static void unmark_task_undone(Task[] taskList, String text) {
        Task task = taskList[Integer.parseInt(text) - 1];
        task.markUndone();
        System.out.println("Ok! I've marked this task as undone:");
        System.out.println("     " + task.getDescriptionWithCheckbox());
    }

    public static void print_all_task(Task[] taskList, int size) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= size; ++i) {
            String taskNumber = String.format("%3d. ", i);
            System.out.println(taskNumber + taskList[i - 1].getDescriptionWithCheckbox());
        }
    }

    public static void goodbye_world() {
        System.out.println("Bye. Hope to see you again soon!");
        print_thank_you();
        print_divider_line();
    }

    public static void print_divider_line() {
        System.out.println("═".repeat(80));
    }

    public static void printName() {
        String name = "\n" +
                "     ██╗██╗███╗   ██╗ ██████╗     ███████╗██╗  ██╗███████╗███╗   ██╗ ██████╗ \n" +
                "     ██║██║████╗  ██║██╔════╝     ██╔════╝██║  ██║██╔════╝████╗  ██║██╔════╝ \n" +
                "     ██║██║██╔██╗ ██║██║  ███╗    ███████╗███████║█████╗  ██╔██╗ ██║██║  ███╗\n" +
                "██   ██║██║██║╚██╗██║██║   ██║    ╚════██║██╔══██║██╔══╝  ██║╚██╗██║██║   ██║\n" +
                "╚█████╔╝██║██║ ╚████║╚██████╔╝    ███████║██║  ██║███████╗██║ ╚████║╚██████╔╝\n" +
                " ╚════╝ ╚═╝╚═╝  ╚═══╝ ╚═════╝     ╚══════╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═══╝ ╚═════╝ \n";
        System.out.print(name);
    }

    public static void print_thank_you() {
        String thankYou = "\n" +
                "████████╗██╗  ██╗ █████╗ ███╗   ██╗██╗  ██╗    ██╗   ██╗ ██████╗ ██╗   ██╗\n" +
                "╚══██╔══╝██║  ██║██╔══██╗████╗  ██║██║ ██╔╝    ╚██╗ ██╔╝██╔═══██╗██║   ██║\n" +
                "   ██║   ███████║███████║██╔██╗ ██║█████╔╝      ╚████╔╝ ██║   ██║██║   ██║\n" +
                "   ██║   ██╔══██║██╔══██║██║╚██╗██║██╔═██╗       ╚██╔╝  ██║   ██║██║   ██║\n" +
                "   ██║   ██║  ██║██║  ██║██║ ╚████║██║  ██╗       ██║   ╚██████╔╝╚██████╔╝\n" +
                "   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝       ╚═╝    ╚═════╝  ╚═════╝\n";
        System.out.print(thankYou);
    }
}