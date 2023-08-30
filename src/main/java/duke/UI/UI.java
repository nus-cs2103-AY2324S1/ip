package duke.UI;

import duke.taskList.Task;
import duke.taskList.TaskList;

import java.util.ArrayList;
import java.util.Scanner;


public class UI {
    private final Scanner sc;
    public UI() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hey summoner! I'm Three Wolves B.\n" + "What do you want me to do?\n");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showMessage(String s) {
        System.out.println(s);
    }

    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }

    public void addTask(Task t, TaskList lst) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + lst.size() + " tasks in the list.\n");
    }

    public void list(TaskList lst) {
        System.out.println("Here are the tasks in your list:");
        lst.forEach(x -> System.out.println(lst.indexOf(x) + 1 + ". " + x));
    }

    public void mark(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t + "\n");
    }

    public void unmark(Task t) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t + "\n");
    }

    public void delete(Task t) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
    }

    /**
     * Displays a list of tasks that match the search criteria.
     * If the provided list is empty, a message indicating no matching tasks is displayed.
     *
     * @param lst The list of tasks that match the search criteria.
     */
    public void showMatch(ArrayList<Task> lst) {
        if (lst.isEmpty()) {
            System.out.println("There is no task matching your request");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            lst.forEach(x -> System.out.println(lst.indexOf(x) + 1 + ". " + x));
        }
    }
}
