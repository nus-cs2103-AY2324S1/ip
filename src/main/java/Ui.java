import java.util.ArrayList;

public class Ui {

    public static void greetings() {
        showLine();
        System.out.println(" Hello! I'm Paimon");
        System.out.println(" What can I do for you?");
        showLine();
    }

    public static void exit() {
        showLine();
        System.out.println(" Bye. Hope to see you again soon!");
        showLine();
    }

    public static void addTask(Task task, int taskCount) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task.toString());
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
    }

    public static void markTask(Task task, int count) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task.toString());
        System.out.println(" Now you have " + count + " tasks in the list.");
    }

    public static void unMarkTask(Task task, int count) {
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task.toString());
        System.out.println(" Now you have " + count + " tasks in the list.");
    }

    public static void deleteTask(Task task, int count) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task.toString());
        System.out.println(" Now you have " + count + " tasks in the list.");
    }

    public static void noTaskList() {
        showLine();
        System.out.println("No existing tasks, creating new task list...");
        showLine();
    }

    public static void listTasks(TaskList taskList) {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskList.taskCount(); i++) {
            System.out.println(" " + (i + 1) + "." + taskList.getTask(i).toString());
        }
    }

    public static void showLine() {
        System.out.println("---------------------------------------------------------------");
    }

    public static void showError(DukeException e) {
        System.out.println(e.toString());
    }
}
