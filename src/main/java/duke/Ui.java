package duke;

import duke.*;

/**
 * Prints messages to interact with user.
 *
 * @author Qin Yan Er
 */
public class Ui {

    /**
     * Creates a new Ui instance.
     */
    public Ui() {}
    /**
     * Prints the welcome message when program just started running.
     */
    public void showIntro() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints when program is unable to load tasks from file.
     */
    public void showLoadingError() {
        System.out.println("Loading Error");
    }

    /**
     * Prints when user exits the program.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the task to be removed and how many tasks are left in the list.
     *
     * @param list the list of tasks.
     * @param removedTask the task to be removed.
     */
    public void removeTask(TaskList list, Task removedTask) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask.toString());
        System.out.println("Now you have " + list.getSize() + " tasks in the list.");
    }

    /**
     * Prints when users asks for the list of tasks.
     *
     * @param list the list of tasks.
     */
    public void printList(TaskList list) {
        if (list.getSize() == 0) {
            System.out.println("There are no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.getSize(); i++) {
                System.out.println((i + 1) + ". " + list.getTask(i).toString());
            }
        }
    }

    /**
     * Prints when user marks a task as done.
     *
     * @param list the list of tasks.
     * @param number the task number to be marked as done.
     */
    public void mark(TaskList list, int number) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.getTask(number).toString());
    }

    /**
     * Prints when user unmarks a done task back to not done yet.
     *
     * @param list the list of tasks.
     * @param number the task number to be unmarked.
     */
    public void unMark(TaskList list, int number) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list.getTask(number).toString());
    }

    /**
     * Prints when user wants to add a todo to the task list.
     *
     * @param list the list of tasks.
     * @param newTodo the new todo to be added.
     */
    public void addTodo(TaskList list, Todo newTodo) {
        System.out.println("Got it. I've added this task:");
        System.out.println(newTodo.toString());
        System.out.println("Now you have " + list.getSize() + " tasks in the list.");
    }

    /**
     * Prints when user wants to add a deadline to task list.
     *
     * @param list the list of tasks.
     * @param newDeadline the new deadline to be added.
     */
    public void addDeadline(TaskList list, Deadline newDeadline) {
        System.out.println("Got it. I've added this task:");
        System.out.println(newDeadline.toString());
        System.out.println("Now you have " + list.getSize() + " tasks in the list.");
    }

    /**
     * Prints when user wants to add an event to task list.
     *
     * @param list the list of tasks.
     * @param newEvent the new event to be added.
     */
    public void addEvent(TaskList list, Event newEvent) {
        System.out.println("Got it. I've added this task:");
        System.out.println(newEvent.toString());
        System.out.println("Now you have " + list.getSize() + " tasks in the list.");
    }

}
