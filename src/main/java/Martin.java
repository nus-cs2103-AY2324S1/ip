import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Martin {
    private static List<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printMessage("Hello! I'm Martin\n     What can I do for you?");
        
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                printMessage("Bye. Hope to see you again soon!");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                printTasks();
            } else if (input.startsWith("mark")) {
                markTask(input);
            } else if (input.startsWith("unmark")) {
                unmarkTask(input);
            } else if (input.startsWith("todo")) {
                addTodo(input);
            } else if (input.startsWith("deadline")) {
                addDeadline(input);
            } else if (input.startsWith("event")) {
                addEvent(input);
            } else {
                printMessage("Please enter a valid command.");
            }
        }
    }

    private static void printMessage(String message) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     " + message);
            System.out.println("    ____________________________________________________________");
    }

    private static void printTasks() {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }

    private static void markTask(String command) {
        try {
            int taskNo = Integer.parseInt(command.split(" ")[1]);
            Task task = tasks.get(taskNo - 1);
            if (task.isDone()) {
                printMessage("Task \"" + task.getDescription() + "\" is already done.");
            } else {
                task.markAsDone();
                printMessage("Nice! I've marked this task as done:\n       " + task);
            }
        } catch (Exception e) {
            printMessage("Invalid task number.");
        }
    }

    private static void unmarkTask(String command) {
        try {
            int taskNo = Integer.parseInt(command.split(" ")[1]);
            Task task = tasks.get(taskNo - 1);
            if (!task.isDone()) {
                printMessage("Task \"" + task.getDescription() + "\" is not done yet.");
            } else {
                task.unmarkAsDone();
                printMessage("OK, I've marked this task as not done yet:\n       " + task);
            }
        } catch (Exception e) {
            printMessage("Invalid task number.");
        }
    }
    
    private static void addTodo(String command) {
        String description = command.substring(5);
        tasks.add(new Todo(description));
        printMessage("Got it. I've added this task:\n       " + tasks.get(tasks.size() - 1) + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    private static void addDeadline(String command) {
        String[] parts = command.substring(9).split(" /by ");
        tasks.add(new Deadline(parts[0], parts[1]));
        printMessage("Got it. I've added this task:\n       " + tasks.get(tasks.size() - 1) + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    private static void addEvent(String command) {
        String[] parts = command.substring(6).split(" /from ");
        String[] timeParts = parts[1].split(" /to ");
        tasks.add(new Event(parts[0], timeParts[0], timeParts[1]));
        printMessage("Got it. I've added this task:\n       " + tasks.get(tasks.size() - 1) + "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
