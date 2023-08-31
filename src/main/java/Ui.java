import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the welcome message.
     */
    public void showWelcomeMsg() {
        System.out.println("Hello! I'm Albatross");
        System.out.println("Please enter a command");
    }

    /**
     * Prints the goodbye message.
     */
    public void showGoodbyeMsg() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    /**
     * Prints a message when a task has been successfully added.
     *
     * @param taskString The string representation of the added task.
     * @param index The number of tasks in the list.
     */
    public void successfulAddTaskMsg(String taskString, int index) {
        System.out.println("Got it. I've added this task:");
        System.out.println(taskString);
        System.out.println("Now you have " + index + " tasks in the list.");
    }

    /**
     * Prints a message when a task has been successfully marked as done.
     *
     * @param taskString The string representation of the task.
     */
    public void successfulMarkDoneMsg(String taskString) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskString);
    }

    /**
     * Prints a message when a task has been successfully marked as not done.
     *
     * @param taskString The string representation of the task.
     */
    public void successfulMarkNotDoneMsg(String taskString) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskString);
    }

    /**
     * Prints a message when a task has been successfully deleted.
     *
     * @param taskString The string representation of the added task.
     * @param index The number of tasks in the list.
     */
    public void successfulTaskDeletionMsg(String taskString, int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskString);
        System.out.println("Now you have " + index + " tasks in the list.");
    }

    /**
     * Prints all tasks in the task list.
     *
     * @param taskList The task list being printed.
     */
    public void printTaskList(TaskList taskList) {
        ArrayList<Task> list = taskList.getList();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                System.out.println((i+1) + ". " + list.get(i).displayableForm());
            }
        }
    }
}
