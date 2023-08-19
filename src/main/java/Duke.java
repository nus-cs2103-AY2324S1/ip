import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String divider = "____________________________________________________________";
    private static String indent = "     ";
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void formatString(String s) {
        System.out.println(indent + s);
    }

    public static void printDivider() {
        formatString(divider);
    }

    public static void greet() {
        String chatbotName = "Miles";

        printDivider();
        formatString("Hey! I'm " + chatbotName + "!");
        formatString("What can I do for you, my friend?");
        printDivider();
    }

    public static void exit() {
        printDivider();
        formatString("Stay safe my friend. Hope to see you again soon man.");
        printDivider();
    }

    public static void markTaskAsDone(int taskNum) {
        Task task = taskList.get(taskNum - 1);
        task.markAsDone();
        printDivider();
        formatString(" Nice! I've marked this task as done:");
        formatString("  " + task.toString());
        printDivider();
    }

    public static void markTaskAsUndone(int taskNum) {
        Task task = taskList.get(taskNum - 1);
        task.markAsUndone();
        printDivider();
        formatString(" OK, I've marked this task as not done yet:");
        formatString("  " + task.toString());
        printDivider();
    }

    public static int getTaskNumberFromCommand(String command, String input) {
        String taskNum = input.replace(command + " ", "");
        return Integer.valueOf(taskNum);
    }

    public static void printList() {
        int n = taskList.size();

        printDivider();
        formatString(" Right now, you have these tasks in your list:");
        for (int i = 0; i < n; i += 1) {
            Task currentTask = taskList.get(i);
            String output = " " + (i + 1) + ". " + currentTask.toString();
            formatString(output);
        }
        printDivider();
    }

    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        String add = "added: ";

        while (true) {
            String input = scanner.nextLine();
            
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                printList();
            } else if (input.contains("unmark")) {
                // mark is in the word unmark so this case has to go first
                int taskNum = getTaskNumberFromCommand("unmark", input);
                markTaskAsUndone(taskNum);
            } else if (input.contains("mark")) {
                int taskNum = getTaskNumberFromCommand("mark", input);
                markTaskAsDone(taskNum);
            } else {
                // store each item in the arraylist
                Task newTask = new Task(input);
                taskList.add(newTask);
                String output = " " + add + input;
                printDivider();
                formatString(output);
                printDivider();
            }
        }
    }

    public static void main(String[] args) {
        Duke.greet();
        Duke.echo();
        Duke.exit();
    }
}
