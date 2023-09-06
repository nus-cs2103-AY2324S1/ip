
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

    public String bye() {
        sc.close();
        return "Bye. Hope to see you again soon!\n__________________________________________________________";
    }

    public String readInput() {
        return sc.nextLine();
    }

    public String displayList(TaskList list) {
        String res = "";
        res += "Here are the tasks in your list:\n";
        res += list.toString();
        res += "\n__________________________________________________________";
        return res;
    }

    public String showAdded(Task task, int len) {
        String res = "";
        res += "Got it. I've added this task:\n";
        res += task.toString();
        res += "\nNow I have " + len + " tasks in the list";
        return res;
    }

    public String showDel(Task task, int len) {
        String res = "";
        res += "Noted. I've removed this task:\n";
        res += task.toString();
        res += "\nNow you have " + len +  " tasks in the list";
        return res;
    }

    public String showDone(Task task) {
        String res = "";
        res += "Nice! I've marked this task as done:\n" + task.toString();
        return res;
    }

    public String showUnDone(Task task) {
        String res = "";
        res += "OK, I've marked this task as not done yet:\n";
        res += task.toString();
        return res;
    }

    public String showException(Exception e) {
        return (e.getMessage());
    }
}
