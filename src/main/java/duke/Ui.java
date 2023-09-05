package duke;

import duke.Task;

import java.util.List;

/**
 * Represents the user interface for the Duke program.
 */
public class Ui {
    public Ui() {

    }

    /**
     * Displays a message asking the user to specify the content of a todo list item.
     */
    public void specify() {
        System.out.println("Please specify the content of the todo list");
    }

    /**
     * Displays a message indicating that a task has been removed.
     *
     * @param task The removed task.
     */
    public void remove(String task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
    }

    /**
     * Displays a message asking the user to input an integer to select a task.
     */
    public void numExc() {
        System.out.println("to pick which task to do, please input an integer");
    }

    /**
     * Displays a message indicating that the specified task index does not exist in the task list.
     */
    public void indexOut() {
        System.out.println("currently, your task list does not contain the task with the index you just inputted");
    }

    /**
     * Displays the list of tasks.
     *
     * @param list The list of tasks to display.
     */
    public void list(List<Task> list) {
        int index = 1;
        System.out.println("Here are the tasks in your list:");
        System.out.println("____________________________________________________________");
        for (Task thing: list) {
            System.out.println(index +". " + thing.toString());
            index++;
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the matching list of tasks.
     *
     * @param list The matching list of tasks to display.
     */
    public void matchingList(List<Task> list) {
        int index = 1;
        System.out.println("Here are the matching tasks in your list:");
        System.out.println("____________________________________________________________");
        for (Task thing: list) {
            System.out.println(index +". " + thing.toString());
            index++;
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message indicating that the user input was a blank space.
     */
    public void blank() {
        System.out.println("Don't just input blank space");
    }

    /**
     * Displays a message indicating that the user input was not in the correct format.
     */
    public void format() {
        System.out.println("Please input the correct format");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param size        The size of the task list.
     * @param onetwo      "task" if there is only one task, "tasks" otherwise.
     * @param description The description of the marked task.
     */
    public void mark(int size, String onetwo, String description) {
        System.out.println("Noted. I've marked this task: ");
        System.out.println("    [X] " + description);
        System.out.println("Now you have " + size + onetwo +  " in the list");
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param size        The size of the task list.
     * @param onetwo      "task" if there is only one task, "tasks" otherwise.
     * @param description The description of the unmarked task.
     */
    public void unmark(int size, String onetwo, String description) {
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println("    [ ] " + description);
        System.out.println("Now you have " + size + onetwo +  " in the list");
    }

    /**
     * Displays a message indicating the current number of tasks in the list.
     *
     * @param size   The size of the task list.
     * @param onetwo "task" if there is only one task, "tasks" otherwise.
     */
    public void currentlist(int size, String onetwo) {
        System.out.println("Now you have " + size + onetwo +  " in the list");
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task   The added task.
     * @param size   The size of the task list.
     * @param onetwo "task" if there is only one task, "tasks" otherwise.
     */
    public void add(Task task, int size, String onetwo) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + onetwo +  " in the list");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a farewell message.
     */
    public void bye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a greeting message.
     */
    public void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello, I'm Zenith" );
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }
}
