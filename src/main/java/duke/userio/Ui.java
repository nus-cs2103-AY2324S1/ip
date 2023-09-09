package duke.userio;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

/**
 * Class to generate appropriate responses.
 */
public class Ui {
    public Ui() {}

    /**
     * Greets the user.
     */
    public String greetings() {
        return ("Hello! I'm ChadBob.\n"
                + "What can I do for you?\n");
    }

    /**
     * Say bye to user and exit the program.
     */
    public String bye() {
        return ("Bye. Hope to see you again soon!\n");
    }

    /**
     * Lists out the tasks in list.
     * @param taskList List of tasks, usually obtained from TaskList class.
     */
    public String list(String taskList) {
        return ("Here are the tasks in your list:\n"
                + taskList);
    }

    /**
     * Unmarks a task and can be seen in output of list method.
     * @param task Target task to be unmarked.
     */
    public String unmarkTask(Task task) {
        return ("OK, I've marked this task as not done yet:\n"
                + task + "\n");
    }

    /**
     * Marks a task and can be seen in output of list method.
     * @param task Target task to be marked.
     */
    public String markTask(Task task) {
        return ("Nice! I've marked this task as done:\n"
                + task + "\n");
    }

    /**
     * Informs that a ToDo task has been added and the total number of tasks in the list.
     * @param toDoTask ToDo task to be added.
     * @param taskList TaskList that contains the task.
     */
    public String toDoAdded(ToDo toDoTask, TaskList taskList) {
        return ("Got it. I've added this task:\n"
                + toDoTask + "\n"
                + "Now you have " + taskList.getSize() + ((taskList.getSize() > 1) ? " tasks " : " task ")
                + "in the list." + "\n");
    }

    /**
     * Informs that there is missing content in the todo task that user tried to create.
     */
    public String toDoMissingContent() {
        return ("☹ OOPS!!! The description of a todo cannot be empty.\n");
    }

    /**
     * Informs that a Deadline task has been added and the total number of tasks in the list.
     * @param deadlineTask Deadline task to be added.
     * @param taskList TaskList containing the Deadline task.
     */
    public String deadlineAdded(Deadline deadlineTask, TaskList taskList) {
        return ("Got it. I've added this task:\n"
                + deadlineTask + "\n"
                + "Now you have " + taskList.getSize() + ((taskList.getSize() > 1) ? " tasks " : " task ")
                + "in the list." + "\n");
    }

    /**
     * Informs user that there is missing content in the Deadline task that user tried to create.
     */
    public String deadlineMissingContent() {
        return ("☹ OOPS!!! The deadline needs to have a task description and /by .\n");
    }

    /**
     * Informs that an Event task has been added and the total number of tasks in the list.
     * @param eventTask Event task to be added.
     * @param taskList TaskList containing Event task.
     */
    public String eventAdded(Event eventTask, TaskList taskList) {
        return ("Got it. I've added this task:\n"
                + eventTask + "\n"
                + "Now you have " + taskList.getSize() + ((taskList.getSize() > 1) ? " tasks " : " task ")
                + "in the list." + "\n");
    }

    /**
     * Informs user that there is missing content in the Event task that user tried to create.
     */
    public String eventMissingContent() {
        return ("☹ OOPS!!! The event needs to have a task description, /from and /to.\n");
    }

    /**
     * Informs the user that the task has been successfully deleted.
     * @param deletedTask Task to be deleted.
     * @param taskList TaskList that contains the task.
     */
    public String taskDeleted(Task deletedTask, TaskList taskList) {
        return ("Noted. I've removed this task:\n"
                + deletedTask + "\n"
                + "Now you have " + taskList.getSize() + ((taskList.getSize() > 1) ? " tasks " : " task ")
                + "in the list." + "\n");
    }

    /**
     * Informs user the tasks found based on the Find functionality.
     * @param tasks Matching tasks gathered from Find functionality.
     */
    public String findResponse(String tasks) {
        return ("Here are the matching tasks in your list:\n"
                + tasks);
    }

    /**
     * Informs user update is successful and show the updated task list.
     * @param taskList Updated task list.
     * @return String to notify update success and updated task list.
     */
    public String updateResponse(String taskList) {
        return ("Update successful! This is the updated task list:\n" + taskList);
    }

    public String updateFailedResponse() {
        return ("Update was unsuccessful :( Please return in this format:\n" +
                "update {task index in task list} {attribute of task} {content to update with} while ensuring the task" +
                " has the necessary attribute (See end of message) \n" +
                "For example: update 1 from 10/10/2023 , update 3 description Wash dishes\n\n" +
                "ToDo: description\n" +
                "Deadline: description, by\n" +
                "Event: description, from, to");
    }
    /**
     * Informs user the given input does not work.
     */
    public String invalidInputRes() {
        return ("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }
}
