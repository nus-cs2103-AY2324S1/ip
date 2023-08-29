import java.util.ArrayList;

public class Ui {
    public static void greet() {
        System.out.println("Hello! I'm Siyuan");
        System.out.println("What can I do for you?");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printError(DukeException err) {
        System.out.println(err.toString());
    }

    public static void addTask(Task tsk, int taskNumber) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tsk.toString());
        System.out.println("Now you have " + taskNumber + " tasks in the list.");
    }

    public static void deleteTask(Task tsk, int taskNumber) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tsk.toString());
        System.out.println("Now you have " + taskNumber + " tasks in the list.");
    }

    public static void lsitAllTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    public static void markAsDone(Task tsk) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tsk.toString());
    }

    public static void markAsUndone(Task tsk) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tsk.toString());
    }
}
