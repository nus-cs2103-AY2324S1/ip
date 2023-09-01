package duke;
import duke.task.Task;

import java.util.Scanner;

public class Ui {

    static final String LOGO = "   /\\_/\\  \n" +
            "  ( o.o ) \n" +
            "   > ^ <\n";
    static final String GREETING = "Hello(@.@), I'm NiHao \nWhat can I do for you?";

    static final String EXIT = "Bye(T.T), Hope to see you again soon!";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine().trim();
    }

    public void showWelcomeMessage() {
        System.out.println(LOGO);
        System.out.println(GREETING);
    }

    public void showGoodByeMessage() {
        System.out.println(EXIT);
    }

    public void printList(TaskList taskList) throws DukeException

    {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.getTask(i).toString());
        }
    }

    public void printMarkMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" +
                "    " + task.toString());
    }

    public void printUnmarkMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" +
                "    " + task.toString());
    }

    public void printAddTaskMessage(Task task, TaskList taskList) {
        System.out.println("Got it, I've added this task:\n    "  +
                task.toString() + "\n" +
                "Now you have " + taskList.size() + " tasks in the list.");
    }

    public void printDeleteMessage(Task task, TaskList taskList) {
        System.out.println("Noted. I've removed this task:\n" +
                "    " + task.toString() + "\n" +
                "Now you have " + taskList.size() + " tasks in the list.");

    }

    public void printMatchingList(TaskList taskList) throws DukeException

    {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.getTask(i).toString());
        }
    }

    public void showErrorMessage(String s) {
        System.out.println(s);
    }

    public void showLine() {
        System.out.println("_____________________________________________________________________________________");
    }

}
