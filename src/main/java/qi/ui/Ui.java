package qi.ui;

import qi.task.Task;
import qi.tasklist.TaskList;

import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        showLine();
        System.out.println("     Hello! I'm Qi");
        System.out.println("     What can I do for you?");
        showLine();
        System.out.println();
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public void showTaskAdded(Task task, TaskList list) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("     Now you have " + list.size() +  " tasks in the list.");
    }

    public void showTaskDeleted(int taskId, TaskList list) {
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + list.deleteTask(taskId));
        System.out.println("     Now you have " + list.size() + " tasks in the list.");
    }

    public void showTaskMarked(int taskId, TaskList list) {
        list.mark(taskId, true);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + list.showTask(taskId));
    }

    public void showTaskUnmarked(int taskId, TaskList list) {
        list.mark(taskId, false);
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + list.showTask(taskId));
    }

    public void showList(TaskList list) {
        System.out.println("     Here are the tasks in your list:");
        System.out.println(list);
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void showInvalid() {
        System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void showGoodbye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.out.println("");
    }

    public void showError(String e) {
        System.out.println("     " + e);
    }
}
