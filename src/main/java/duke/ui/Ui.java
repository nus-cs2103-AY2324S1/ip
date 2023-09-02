package duke.ui;
import duke.task.Task;
import duke.task.TaskList;
import java.util.Scanner;

public class Ui {
    Scanner sc;
    public Ui() {
        this.sc = new Scanner(System.in);
    }
    public void showWelcome() {
        String Introduction = "____________________________________________________________\n" +
                " Hello! I'm FootyCouch\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(Introduction);
    }
    public void showLine() {
        System.out.println("____________________________________________________________");
    }
    public void showError(String message) {
        System.out.println(" ☹ OOPS!!! " + message);
    }
    public void printAdd(Task task, TaskList tasks) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.length() + " tasks in the list.");
    }
    public void printDelete(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println("Now you have " + tasks.length() + " tasks in the list.");
    }
    public void printExit() {
        System.out.println(" Bye. Hope to see you again soon!");
    }
    public void printMark(Task task) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
    }
    public void printUnmark(Task task) {
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
    }
    public void printList(TaskList tasks) {
        System.out.println(tasks);
    }
    public String readCommand() {
        return sc.nextLine();
    }
}