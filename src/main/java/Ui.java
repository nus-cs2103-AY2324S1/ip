import java.util.ArrayList;

public class Ui {

    public static void printGreetings() {
        printLine();
        System.out.println(" Hello! I'm Dukduk");
        System.out.println(" What can I do for you?");
        printLine();
    }

    public static void printExit() {
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printTasks(ArrayList<Task> tasks) {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
    }

    public static void addTask(ArrayList<Task> tasks) {
        System.out.println(" Got it. I've added this task:\n   "
                + tasks.get(tasks.size() - 1).toString());
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void deleteTask(ArrayList<Task> tasks, int taskIndex, Task removedTask) {
        System.out.println(" Noted. I've removed this task:\n   " + removedTask);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void markAsDone(ArrayList<Task> tasks, int taskIndex) {
        System.out.println(" Nice! I've marked this task as done:\n ["
                + tasks.get(taskIndex).getStatusIcon() + "] "
                + tasks.get(taskIndex).getDescription());
    }

    public static void markAsNotDone(ArrayList<Task> tasks, int taskIndex) {
        System.out.println(" OK, I've marked this task as not done yet:\n ["
                + tasks.get(taskIndex).getStatusIcon() + "] "
                + tasks.get(taskIndex).getDescription());
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printErrorMsg(DukdukException e) {
        System.out.println(" ☹ " + e.getMessage());
    }
}
