package duke.userio;

import duke.task.*;

/**
 * Class to generate appropriate responses.
 */
public class Ui {
    protected static String H_LINE = "____________________________________________________________\n";
    public Ui() {}

    /**
     * Greets the user.
     */
    public void greetings() {
        System.out.println(H_LINE
                + "Hello! I'm ChadBob.\n"
                + "What can I do for you?\n"
                + H_LINE);
    }

    /**
     * Say bye to user and exit the program.
     */
    public void bye() {
        System.out.println(H_LINE
                + "Bye. Hope to see you again soon!\n"
                + H_LINE);
    }

    /**
     * Lists out the tasks in list.
     * @param taskList List of tasks, usually obtained from TaskList class.
     */
    public void list(String taskList) {
        System.out.println(H_LINE
                + "Here are the tasks in your list:\n"
                + taskList
                + H_LINE);
    }

    /**
     * Unmarks a task and can be seen in output of list method.
     * @param task Target task to be unmarked.
     */
    public void unmarkTask(Task task) {
        System.out.println(H_LINE
                + "OK, I've marked this task as not done yet:\n"
                + task + "\n"
                + H_LINE);
    }

    /**
     * Marks a task and can be seen in output of list method.
     * @param task Target task to be marked.
     */
    public void markTask(Task task) {
        System.out.println(H_LINE
                + "Nice! I've marked this task as done:\n"
                + task + "\n"
                + H_LINE);
    }

    /**
     * Informs that a ToDo task has been added and the total number of tasks in the list.
     * @param toDoTask ToDo task to be added.
     * @param taskList TaskList that contains the task.
     */
    public void toDoAdded(ToDo toDoTask, TaskList taskList) {
        System.out.println(H_LINE
                + "Got it. I've added this task:\n"
                + toDoTask + "\n"
                + "Now you have " + taskList.getSize() + ((taskList.getSize() > 1) ? " tasks " : " task ")
                + "in the list." + "\n"
                + H_LINE);
    }

    /**
     * Informs that there is missing content in the todo task that user tried to create.
     */
    public void toDoMissingContent() {
        System.out.println(H_LINE
                + "☹ OOPS!!! The description of a todo cannot be empty.\n"
                + H_LINE);
    }

    /**
     * Informs that a Deadline task has been added and the total number of tasks in the list.
     * @param deadlineTask Deadline task to be added.
     * @param taskList TaskList containing the Deadline task.
     */
    public void deadlineAdded(Deadline deadlineTask, TaskList taskList) {
        System.out.println(H_LINE
                + "Got it. I've added this task:\n"
                + deadlineTask + "\n"
                + "Now you have " + taskList.getSize() + ((taskList.getSize() > 1) ? " tasks " : " task ")
                + "in the list." + "\n"
                + H_LINE);
    }

    /**
     * Informs user that there is missing content in the Deadline task that user tried to create.
     */
    public void deadlineMissingContent() {
        System.out.println(H_LINE
                + "☹ OOPS!!! The deadline needs to have a task description and /by .\n"
                + H_LINE);
    }

    /**
     * Informs that a Event task has been added and the total number of tasks in the list.
     * @param eventTask Event task to be added.
     * @param taskList TaskList containing Event task.
     */
    public void eventAdded(Event eventTask, TaskList taskList) {
        System.out.println(H_LINE
                + "Got it. I've added this task:\n"
                + eventTask + "\n"
                + "Now you have " + taskList.getSize() + ((taskList.getSize() > 1) ? " tasks " : " task ")
                + "in the list." + "\n"
                + H_LINE);
    }

    /**
     * Informs user that there is missing content in the Event task that user tried to create.
     */
    public void eventMissingContent() {
        System.out.println(H_LINE
                + "☹ OOPS!!! The event needs to have a task description, /from and /to.\n"
                + H_LINE);
    }

    /**
     * Informs the user that the task has been successfully deleted.
     * @param deletedTask Task to be deleted.
     * @param taskList TaskList that contains the task.
     */
    public void taskDeleted(Task deletedTask, TaskList taskList) {
        System.out.println(H_LINE
                + "Noted. I've removed this task:\n"
                + deletedTask + "\n"
                + "Now you have " + taskList.getSize() + ((taskList.getSize() > 1) ? " tasks " : " task ")
                + "in the list." + "\n"
                + H_LINE);
    }

    /**
     * Informs user the tasks found based on the Find functionality.
     * @param tasks Matching tasks gathered from Find functionality.
     */
    public void findResponse(String tasks) {
        System.out.println(H_LINE
                + "Here are the matching tasks in your list:\n"
                + tasks
                + H_LINE);
    }

    /**
     * Informs user the given input does not work.
     */
    public void invalidInputRes() {
        System.out.println(H_LINE
                + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + H_LINE);
    }
}
