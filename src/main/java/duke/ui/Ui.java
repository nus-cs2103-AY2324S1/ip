package duke.ui;

import duke.exception.ChatException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    private Scanner sc;
    public Ui() {
        this.sc = new Scanner(System.in);
    }
    public String readCommand() {
        return sc.nextLine();
    }
    public void greetResponse() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }
    public void formatTaskResponse(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:\n  " + task
                + "\nNow you have " + tasks.getSize() + " tasks in the list."); ;
    }
    public void listTaskResponse(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < tasks.getSize() + 1; i++) {
            System.out.println(i + ". " + tasks.getTask(i).toString());
        }
    }
    public void deleteTaskResponse(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task:\n" + task
                + "\nNow you have " + tasks.getSize() + " tasks in the list.");
    }
    public void markDoneResponse(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }
    public void markUndoneResponse(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }
    public void exitResponse() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public void showLoadingError(ChatException e) {
        System.out.println(e);
    }
    public void prompt() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n" +
                "I can understand the following commands: \n" +
                "1. todo description \n" +
                "2. deadline description /by date (yyyy-mm-dd)\n" +
                "3. event description /from time /to time\n" +
                "4. list\n" +
                "5. mark task number\n" +
                "6. unmark task number\n" +
                "7. delete task number\n" +
                "8. bye");
    }
}
