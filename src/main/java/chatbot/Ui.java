package chatbot;

import chatbot.exception.InvalidTaskNumberException;
import chatbot.task.Task;

import java.util.Scanner;

public class Ui {
    /** Name of the Chatbot associated with the Ui instance. */
    private String name;

    /** Scanner for the Ui. */
    private Scanner scanner;

    /**
     * Constructor for the Chatbot.
     */
    public Ui(String name) {
        this.name = name;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Greets the user.
     */
    public void greet() {
        System.out.println("Hello! I'm " + this.name + "\n" + "What can I do for you?\n");
    }

    /**
     * Ends user interactions and closes scanner.
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        this.scanner.close();
    }

    /**
     * Prints confirmation after adding task.
     *
     * @param taskList The TaskList containing the task.
     * @throws InvalidTaskNumberException If there is no task with the given index in the taskList.
     */
    public void printAddConfirmation(TaskList taskList) throws InvalidTaskNumberException {
        System.out.println("Got it. I've added this task:\n" + taskList.taskRep(taskList.size() - 1));
        System.out.println("Now you have " + taskList.size() + " tasks in the list.\n");
    }

    /**
     * Prints confirmation after marking task done status as true.
     *
     * @param i The index of the task that is marked as done.
     * @param taskList The TaskList containing the task.
     * @throws InvalidTaskNumberException If there is no task with the given index in the taskList.
     */
    public void printMarkDoneConfirmation(int i, TaskList taskList) throws InvalidTaskNumberException {
        assert i < taskList.size();
        System.out.println("Nice! I've marked this task as done:\n" + taskList.taskRep(i) + "\n");
    }

    /**
     * Prints confirmation after marking task done status as false.
     *
     * @param i The index of the task that is marked as undone.
     * @param taskList The TaskList containing the task.
     * @throws InvalidTaskNumberException If there is no task with the given index in the taskList.
     */
    public void printMarkUndoneConfirmation(int i, TaskList taskList) throws InvalidTaskNumberException {
        assert i < taskList.size();
        System.out.println("OK, I've marked this task as not done yet:\n" + taskList.taskRep(i) + "\n");
    }

    /**
     * Prints confirmation after deleting task.
     *
     * @param i The index of the task that is deleted.
     * @param taskList The TaskList containing the task.
     * @throws InvalidTaskNumberException If there is no task with the given index in the taskList.
     */
    public void printDeleteConfirmation(int i, TaskList taskList) throws InvalidTaskNumberException {
        assert i < taskList.size();
        System.out.println("Noted. I've removed the task!\n");
    }

    /**
     * Prints confirmation after adding task tag.
     */
    public void printAddTaskTagConfirmation() {
        System.out.println("Noted. I've tagged the task!\n");
    }

    /**
     * Lists out all the tasks in taskList.
     *
     * @param taskList The TaskList to list tasks from.
     * @throws InvalidTaskNumberException If there is no task with the given index in the taskList.
     */
    public void listTasks(TaskList taskList) throws InvalidTaskNumberException {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            int num = i + 1;
            System.out.println(num + ". " + taskList.taskRep(i));
        }
        System.out.print("\n");
    }

    /**
     * Lists out the matching tasks in the taskList.
     *
     * @param keyword The keyword to match with.
     * @param taskList The TaskList to print.
     * @throws InvalidTaskNumberException If there is no task with the given index in the taskList.
     */
    public void listMatchingTasks(String keyword, TaskList taskList) throws InvalidTaskNumberException {
        if (taskList.size() > 0) {
            System.out.println("Here are the matching tasks in your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                int num = i + 1;
                System.out.println(num + ". " + taskList.taskRep(i));
            }
        } else {
            System.out.println("There are no matching tasks in your list!");
        }
        System.out.print("\n");
    }

    /**
     * Receives and reads user input.
     *
     * @returns String containing next line of user input.
     */
    public String getUserInput() {
        System.out.print("> ");
        return this.scanner.nextLine();
    }

    /**
     * Prints Exception message.
     *
     * @param e The caught Exception.
     */
    public void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage() + "\n");
    }
}
