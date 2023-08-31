import java.time.LocalDate;
import java.util.List;
public class Ui {

    private static final String INDENT = "    ";

    public void printGreeting() {
        printHorizontalLine();
        printIndented("Hello! I'm Davidson");
        printIndented("What can I do for you?");
        printHorizontalLine();
    }

    public void printExit() {
        printHorizontalLine();
        printIndented("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public void printHorizontalLine() {
        System.out.print(INDENT);
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public void printIndented(String message) {
        System.out.println(INDENT + message);
    }

    public void printList(List<Task> tasks) {
        printHorizontalLine();
        printIndented("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            printIndented((i + 1) + "." + tasks.get(i));
        }
        printHorizontalLine();
    }

    public void printTaskAdded(Task task, int size) {
        printHorizontalLine();
        printIndented("Got it. I've added this task:");
        printIndented("  " + task.toFileString());
        printIndented("Now you have " + size + " tasks in the list.");
        printHorizontalLine();
    }

    public void showError(String message) {
        printIndented(message);
    }

    public void printTasksOnDate(List<Task> tasksOnDate, LocalDate date) {
        printHorizontalLine();
        if (tasksOnDate.isEmpty()) {
            printIndented("No tasks on " + date);
            return;
        }
        printIndented("Here are the tasks on " + date + ":");
        for (Task task : tasksOnDate) {
            printIndented(task.toFileString());
        }
        printHorizontalLine();
    }

    public void printMarkedAsDone(Task task) {
        printHorizontalLine();
        printIndented("Nice! I've marked this task as done:");
        printIndented("  " + task);
        printHorizontalLine();
    }

    public void printMarkedAsNotDone(Task task) {
        printHorizontalLine();
        printIndented("Okay! I've marked this task as not done:");
        printIndented("  " + task);
        printHorizontalLine();
    }

    public void printTaskDeleted(Task removedTask, int size) {
        printHorizontalLine();
        printIndented("Noted. I've removed this task:");
        printIndented("  " + removedTask);
        printIndented("Now you have " + size + " tasks in the list.");
        printHorizontalLine();
    }
}