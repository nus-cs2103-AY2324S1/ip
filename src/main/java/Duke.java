import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String horizontalLine = "      ____________________________________________________________\n";
    public static void greet() {
        System.out.println(horizontalLine
                            + "      " + "Hello! I'm Glenda!\n"
                            + "      " + "What can I do for you?\n"
                            + horizontalLine);
    }
    public static void exit() {
        System.out.println(horizontalLine
                            + "      " + "Bye. Hope to see you again soon!\n"
                            + horizontalLine);
    }

    public static void printCommand(String command) {
        System.out.println(horizontalLine
                            + "      " + "added: " + command + "\n"
                            + horizontalLine);
    }

    public static void printTasks(ArrayList tasks) {
        System.out.print(horizontalLine);

        if (tasks.isEmpty()) {
            // Case where there is no tasks to be displayed
            System.out.println("      " + "No tasks added. ");

        } else {
            int taskNumber = 1;
            for (Object task: tasks) {
                System.out.println("      " + taskNumber + ". " + task);
                taskNumber++;
            }
        }

        System.out.println(horizontalLine);
    }

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();

        while(true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                exit();
                return;
            } else if (command.equals("list")) {
                printTasks(tasks);
            } else {
                printCommand(command);
                tasks.add(command);
            }
        }
    }
}
