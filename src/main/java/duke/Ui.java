
package duke;

import duke.tasks.Deadline;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);

    public void hello() {
        System.out.println("__________________________________________________________");
        System.out.println("Hello im Sori! What can I do for you?");
        System.out.println("__________________________________________________________");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("__________________________________________________________");
        sc.close();
    }

    public String readInput() {
        return sc.nextLine();
    }

    public void displayList(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(list.toString());
        System.out.println("__________________________________________________________");
    }

    public void showAdded(Task task, int len) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now I have " + len + " tasks in the list");
    }

    public void showDel(Task task, int len) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + len +  " tasks in the list");
    }

    public void showDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    public void showUnDone(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    public void showException(Exception e) {
        System.out.println(e.getMessage());
    }
}
