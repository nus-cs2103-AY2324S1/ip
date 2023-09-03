package duke;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    private Scanner input = new Scanner(System.in);

    /**
     * Obtains the user input.
     *
     * @return String representation of the user input.
     */
    public String getInput() {
        return input.nextLine();
    }

    /**
     * Greets the user when the chatbot is started.
     */
    public void printGreeting() {
        String greet = "Hi! I'm Uke\n" + "What can I do for you?\n";
        System.out.println(greet);
    }

    /**
     * Prints the list of tasks in the TaskList given.
     *
     * @param storedTasks TaskList to be printed.
     * @param isListCommand Whether the method is called from a list command.
     */
    public void printList(TaskList storedTasks, boolean isListCommand) {
        int len = storedTasks.getLength();

        if (isListCommand) {
            if (len > 0) {
                System.out.println("Okay! Here's the full list of your added tasks:");
                for (int i = 1; i < len + 1; i++) {
                    System.out.println(i + ". " + storedTasks.getTask(i - 1));
                }
            } else {
                System.out.println("No tasks found!");
            }
        } else {
            if (len > 0) {
                System.out.println("Here's the list of matching tasks I found:");
                for (int i = 1; i < len + 1; i++) {
                    System.out.println(i + ". " + storedTasks.getTask(i - 1));
                }
            } else {
                System.out.println("No matching tasks found :(");
            }
        }
    }

    /**
     * Prints the task that was marked as done.
     *
     * @param t Task that was marked as done.
     */
    public void printMark(Task t) {
        System.out.println("The following task has been marked as done!");
        System.out.println(t);
    }

    /**
     * Prints the task that was marked as undone.
     *
     * @param t Task that was marked as undone.
     */
    public void printUnmark(Task t) {
        System.out.println("The following task has been marked as undone!");
        System.out.println(t);
    }

    /**
     * Prints the task that was deleted.
     *
     * @param t Task that was deleted.
     */
    public void printDelete(Task t) {
        System.out.println("The following task has been successfully deleted: " + t);
    }

    /**
     * Prints the number of tasks in the given TaskList.
     *
     * @param tl TaskList given.
     */
    public void printNumberOfTasks(TaskList tl) {
        int len = tl.getLength();
        System.out.println("You now have " + len + " task(s) in your list.");
    }

    /**
     * Prints the exit message.
     */
    public void printExit() {
        String exit = "Bye. See you soon! :)\n";
        System.out.println(exit);
    }

    /**
     * Prints the error message.
     *
     * @param e Exception which error message is to be printed.
     */
    public void printError(Exception e) {
        System.out.println(e);
    }

    /**
     * Prints the task that was added.
     *
     * @param t Task that was added.
     */
    public void printAdd(Task t) {
        System.out.println("The following task has been successfully added: " + t);
    }
}
