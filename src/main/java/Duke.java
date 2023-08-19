import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String divider = "____________________________________________________________";
    private static String indent = "     ";
    private static ArrayList<Task> taskList = new ArrayList<>();

    /*
     * Method that adds indentation to the string given and prints it out.
     */
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

    /*
     * Method that handles input with the command "mark" or "unmark".
     * 
     * @param command the command that the user inputs: either "mark" or "unmark"
     * @param input   what the user inputs 
     * @return        the task number that the user wants to mark or unmark
     */
    public static int getTaskNumber(String command, String input) {
        String taskNum = input.replace(command + " ", "");
        return Integer.valueOf(taskNum);
    }

    /*
     * Method that prints every task in the task list.
     */
    public static void printList() {
        int n = taskList.size();

        printDivider();
        formatString(" Here are the tasks in your list:");
        for (int i = 0; i < n; i += 1) {
            Task currentTask = taskList.get(i);
            String output = " " + (i + 1) + ". " + currentTask.toString();
            formatString(output);
        }
        printDivider();
    }

    /*
     * Method that accepts a task and prints it out.
     * 
     * @param task the task that the user wants to add (could be any of todo, deadline, event)
     */
    public static void printAddedTask(Task task, int n) {
        printDivider();
        formatString(" Got it. I've added this task:");
        formatString("  " + task.toString());
        formatString(" Now you have " + n + " tasks in the list.");
        printDivider();
    }

    public static void handleCommands() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                printList();
            } else if (input.contains("unmark")) {
                // mark is in the word unmark so this case has to go first
                int taskNum = getTaskNumber("unmark", input);
                markTaskAsUndone(taskNum);
            } else if (input.contains("mark")) {
                int taskNum = getTaskNumber("mark", input);
                markTaskAsDone(taskNum);
            } else if (input.contains("todo")) {
                ToDo newToDo = new ToDo(input);
                taskList.add(newToDo);
                printAddedTask(newToDo, taskList.size());
            } else if (input.contains("deadline")) {
                Deadline newDeadline = new Deadline(input);
                taskList.add(newDeadline);
                printAddedTask(newDeadline, taskList.size());
            } else if (input.contains("event")) {
                Event newEvent = new Event(input);
                taskList.add(newEvent);
                printAddedTask(newEvent, taskList.size());
            }
        }
    }

    public static void main(String[] args) {
        Duke.greet();
        Duke.handleCommands();
        Duke.exit();
    }
}
