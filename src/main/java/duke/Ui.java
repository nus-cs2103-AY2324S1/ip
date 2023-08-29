package duke;

import exception.DukeException;
import task.Task;

import java.io.InputStream;
import java.util.Scanner;

public class Ui {

    private static final String DIVIDER = "____________________________________________________________";
    private final Scanner in;
    private boolean Exit;

    public Ui() {
        this(System.in);
    }
    public Ui(InputStream in) {
        this.Exit = false;
        this.in = new Scanner(in);
    }

    public void handleUserInput(TaskList tasks) {
        try {
            Parser.replyUser(this.in.nextLine(), tasks, this);
        } catch (DukeException e){
            this.showErrorMsg(e);
        }
    }

    public void printGreeting(String name) {
        System.out.println(DIVIDER);
        System.out.println("Hello! I'm " + name + "!");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);
    }

    public void exitGreeting() {
        this.Exit = true;
        System.out.println(DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    public void markSuccess(Task task) {
        System.out.println(DIVIDER);
        System.out.println("Nice! I've marked this task as done: \n" + task);
        System.out.println(DIVIDER);
    }

    public void unMarkSuccess(Task task) {
        System.out.println(DIVIDER);
        System.out.println("OK, I've marked this task as not done yet: \n" + task);
        System.out.println(DIVIDER);
    }

    public void toDoSuccess(Task task, int size) {
        System.out.println(DIVIDER);
        System.out.println("Got it. I've added this task: \n" + task + "\n"
                + "Now you have " + size + " tasks in the list.");
        System.out.println(DIVIDER);

    }

    public void deadLineSuccess(Task task, int size) {
        System.out.println(DIVIDER);
        System.out.println("Got it. I've added this task: \n" + task + "\n"
                + "Now you have " + size + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    public void eventSuccess(Task task, int size) {
        System.out.println(DIVIDER);
        System.out.println("Got it. I've added this task: \n" + task + "\n"
                + "Now you have " + size + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    public void deleteSuccess(Task task, int size) {
        System.out.println(DIVIDER);
        System.out.println("Noted. I've removed this task: \n" + task +
                "\nNow you have " + size + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    /**
     * Output all the user input.
     *
     */
    public void outputList(TaskList tasks) {
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list: ");
        int i = 1;
        for (Task val : tasks.getTasks()) {
            output.append("\n").append(i).append(". ").append(val);
            i++;
        }
        System.out.println(DIVIDER);
        System.out.println(output);
        System.out.println(DIVIDER);

    }
    public void customReply() {
        System.out.println(DIVIDER);
        System.out.println("Hi barbie!");
        System.out.println(DIVIDER);
    }

    public void showErrorMsg(Exception e) {
        System.out.println(DIVIDER);
        System.out.println(e.getMessage());
        System.out.println(DIVIDER);
    }

    public boolean isExit() {
        return this.Exit;
    }

    public void findSuccess(TaskList tasks) {
        StringBuilder output = new StringBuilder();
        output.append("Here are the matching tasks in your list: ");
        int i = 1;
        for (Task val : tasks.getTasks()) {
            output.append("\n").append(i).append(". ").append(val);
            i++;
        }
        System.out.println(DIVIDER);
        System.out.println(output);
        System.out.println(DIVIDER);
    }
}
