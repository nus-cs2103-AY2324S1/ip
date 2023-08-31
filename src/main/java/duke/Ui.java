package duke;

import duke.task.Task;

import java.util.List;

public class Ui {

    public static void greet() {
        System.out.println("---------------------------------------------\n Hello! I'm zy\n" +
                " What can I do for you?\n---------------------------------------------");
    }

    public static void goodbye() {
        System.out.println("---------------------------------------------\n Bye. Hope to see you again soon!" +
                "\n---------------------------------------------");
    }

    public static void printLine() {
        System.out.println("---------------------------------------------");
    }

    public static void listTasks(List<Task> tasks) {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i).toString());
        }
    }

    public static void markDoneMessage() {
        System.out.println(" Nice! I've marked this duke.task as done:");
    }

    public static void markUndoneMessage() {
        System.out.println(" OK, I've marked this duke.task as not done yet:");
    }

    public static void showTaskMessage(Task task) {
        System.out.println("   " + task.toString());
    }

    public static void addTaskMessage(TaskList tasks) {
        System.out.println(" Got it. I've added this duke.task:" + "\n" + "   "
                            + tasks.getAll().get(tasks.getAll().size() - 1).toString() + "\n" + " Now you have "
                            + tasks.getAll().size() + " tasks in the list.");
    }

    public static void removeTaskMessage() {
        System.out.println(" Noted. I've removed this duke.task:");
    }

    public static void showLoadingError(DukeException e) {
        System.out.println(e);
    }
}
