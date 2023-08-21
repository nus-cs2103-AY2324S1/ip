import java.util.Scanner;

public class Duke {
    private static final String NAME = "404";
    private static final String NAME_ART =
            "               _                _               _                      \n" +
            "           _  /\\ \\            / /\\          _  /\\ \\               \n" +
            "          /\\_\\\\ \\ \\          / /  \\        /\\_\\\\ \\ \\        \n" +
            "         / / / \\ \\ \\        / / /\\ \\      / / / \\ \\ \\          \n" +
            "        / / /   \\ \\ \\      / / /\\ \\ \\    / / /   \\ \\ \\        \n" +
            "        \\ \\ \\____\\ \\ \\    /_/ /  \\ \\ \\   \\ \\ \\____\\ \\ \\ \n" +
            "         \\ \\________\\ \\   \\ \\ \\   \\ \\ \\   \\ \\________\\ \\ \n" +
            "          \\/________/\\ \\   \\ \\ \\   \\ \\ \\   \\/________/\\ \\  \n" +
            "                    \\ \\ \\   \\ \\ \\___\\ \\ \\            \\ \\ \\ \n" +
            "                     \\ \\_\\   \\ \\/____\\ \\ \\            \\ \\_\\ \n" +
            "                      \\/_/    \\_________\\/             \\/_/";
    private static final String INDENT = "     ";
    private static final int STORE_SIZE = 100;
    private static Task[] tasks = new Task[STORE_SIZE];
    private static int task_pointer = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String greeting = String.format("%sHello! I'm %s%n%sWhat can I do for you?",
                                         INDENT, NAME, INDENT);
        System.out.println(NAME_ART);
        printLine();
        System.out.println(greeting);
        printLine();
        System.out.println();

        while (true) {
            String text = sc.nextLine();
            String[] split = text.split(" ");
            if (text.isEmpty() || split.length == 0) {
                printLine();
                System.out.printf("%sPlease retry, you have not entered anything!%n", INDENT);
                printLine();
                continue;
            }
            String command = split[0].toLowerCase();
            String message = "";
            if (split.length > 1) {
                message = text.substring(split[0].length() + 1);
            }
            if (text.equals("bye")) {
                break;
            }

            printLine();
            switch(command) {
                case "list":
                    listTask();
                    break;

                case "mark":
                    markAndUnmarkTask(message, true);
                    break;

                case "unmark":
                    markAndUnmarkTask(message, false);
                    break;

                case "todo":
                    Task todo = new Todo(message);
                    addTask(todo);
                    break;

                case "deadline":
                    String[] deadlineTask = message.split(" /by ");
                    addTask(new Deadline(deadlineTask[0], deadlineTask[1]));
                    break;

                case "event":
                    String[] eventTask = message.split(" /from ");
                    String[] dates = eventTask[1].split(" /to ");
                    addTask(new Event(eventTask[0], dates[0], dates[1]));
                    break;
            }
            printLine();
            System.out.println();
        }
        printLine();
        System.out.printf("%sBye. Hope to see you again soon!%n", INDENT);
        printLine();
        sc.close();
    }

    private static void printLine() {
        String line = "    ____________________________________________________________\n" +
                      "   /_____/_____/_____/_____/_____/_____/_____/_____/_____/_____/";
        System.out.println(line);
    }

    private static void addTask(Task task) {
        tasks[task_pointer] = task;
        task_pointer++;
        System.out.printf("%sGot it. I've added this task:%n" +
                          "%s  %s%n" +
                          "%sNow you have %d tasks in the list.%n",
                          INDENT, INDENT, task, INDENT, task_pointer);
    }

    private static void listTask() {
        if (task_pointer == 0) {
            System.out.printf("%sSorry, there is nothing to list!%n", INDENT);
            return;
        } else {
            System.out.printf("%sHere are the tasks in your list:%n", INDENT);
        }

        for (int i = 0; i < task_pointer; i++) {
            if (tasks[i] == null) {
                break;
            } else {
                String out = String.format("%s%d.%s", INDENT, i + 1, tasks[i]);
                System.out.println(out);
            }
        }
    }

    private static void markAndUnmarkTask(String message, boolean mark) {
        String task;
        int index = Integer.valueOf(message) - 1;
        if (mark) {
            message = "Nice! I've marked this task as done:";
            task = tasks[index].markAsDone();
        } else {
            message = "OK, I've marked this task as not done yet:";
            task = tasks[index].markAsNotDone();
        }
        System.out.printf("%s%s%n%s  %s%n", INDENT, message, INDENT, task);
    }
}
