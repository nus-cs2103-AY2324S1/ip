package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {

        scanner = new Scanner(System.in);
    }

    public void introMessage() {
        System.out.println("Hello! I'm BaekBot.\nIf you're unsure of the commands available, type /help. "
                + "\nWhat can I do for you?");
    }

    public String getInput() {
        return scanner.nextLine();
    }

    public void addTaskMessage(Task task, int count) {
        System.out.println("Got it. I've added this task:\n" + task
                + "\nNow you have " + count + " tasks in the list.");
    }

    public void deleteTaskMessage(Task task, int count) {
        System.out.println("Noted. I've removed this task:\n"
                + task + "\nNow you have " + count + " tasks in the list.");
    }

    public void emptyTaskMessage() {
        System.out.println("You don't have any tasks right now.");
    }

    public void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void markDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    public void unmarkDoneMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet: \n" + task);
    }

    public void loadingErrorMessage() {
        System.out.println("There was an issue loading your savefile!");
    }

    public void noCommandMessage() {
        System.out.println("Sorry, I don't understand what it means :(");
    }

    public void emptyCommandMessage() {
        System.out.println("Don't give me an empty command!");
    }

    public void helpMessage() {
        System.out.println("To view the list of tasks, type list. \nTo add a todo, type todo."
                + "\nTo add a deadline, type deadline with /by.\nTo add a event, type event with /from and /to."
                + "\nTo mark/unmark tasks, type mark/unmark followed by the index."
                + "\nTo delete a task, type delete followed by the index."
                + "\nTo exit, type bye.");
    }

    public void closeScanner() {
        scanner.close();
    }
}
