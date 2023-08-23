import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static final String horizontalLine = "    ____________________________________________________________\n";
    public static ArrayList<String> taskList = new ArrayList<>();
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
        } else {
            addTask(command);
            System.out.println(horizontalLine
                    + "     added: " + command + "\n"
                    + horizontalLine);
        }
    }
    public static void addTask(String task) {
        if (Objects.equals(task, "list")) {
            Object[] taskArray = taskList.toArray();

            System.out.println(horizontalLine);
            for (int i = 0; i < taskArray.length; i++) {
                System.out.println("     " + (i + 1) + ". " + taskArray[i]);
            }
            System.out.println(horizontalLine);
        } else {
            taskList.add(task);
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
