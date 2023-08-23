import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static final String horizontalLine = "    ____________________________________________________________\n";
    public static Task[] taskArray = new Task[100];
    public static int numTask = 0;
    public static void greet() {
        System.out.println(horizontalLine
                + "     Hello! I'm POPOOH\n"
                + "     What can I do for you?\n"
                + horizontalLine);
    }
    public static void exit() {
        System.out.println("     Bye. Hope to see you again soon!\n"
                + horizontalLine);
    }
    public static void printCommand(String command) {
        if (Objects.equals(command, "bye")) {
            exit();
        } else if (Objects.equals(command, "list")) {
            addTask(command);
        } else if (command.contains("unmark")) {
            System.out.println(horizontalLine);
            int taskNum = Integer.parseInt(command.substring(7)) - 1;
            taskArray[taskNum].markAsUndone();
            System.out.println(horizontalLine);
        } else if (command.contains("mark")) {
            System.out.println(horizontalLine);
            int taskNum = Integer.parseInt(command.substring(5)) - 1;
            taskArray[taskNum].markAsDone();
            System.out.println(horizontalLine);
        } else {
            addTask(command);
            System.out.println(horizontalLine
                    + "     added: " + command + "\n"
                    + horizontalLine);
        }
    }
    public static void addTask(String task) {
        if (Objects.equals(task, "list")) {
            System.out.println(horizontalLine);
            for (int i = 0; i < numTask; i++) {
                System.out.println("     " + (i + 1) + ". " + taskArray[i].printDesc());
            }
            System.out.println(horizontalLine);
        } else {
            taskArray[numTask] = new Task(task);
            numTask++;
        }
    }
    public static void main(String[] args) {
        greet();
        Scanner input = new Scanner(System.in);

        while (input.hasNext()) {
            String command = input.nextLine();
            printCommand(command);
        }
    }
}
