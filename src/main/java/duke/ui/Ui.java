package duke.ui;

import duke.exception.ChatException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Responses to user commands.
 */
public class Ui {
    /**
     * Response with all tasks in the tasklist listed.
     * @param tasks The tasklist to be listed.
     * @return Response of all tasks listed.
     * @throws ChatException If task index is out of bounds.
     */
    public String listTaskResponse(TaskList tasks) throws ChatException {
        if (tasks.getSize() == 0) {
            return "There are currently no tasks in your list.";
        }
        String output = "Here are the tasks in your list:";
        for (int i = 1; i < tasks.getSize() + 1; i++) {
            output = output.concat(System.lineSeparator() + i + ". " + tasks.getTask(i).toString());
        }
        return output;
    }

    /**
     * Response with all tasks in the tasklist listed for 'find' command.
     * @param tasks The tasklist to be listed.
     * @return Response of all tasks listed.
     * @throws ChatException If task index is out of bounds.
     */
    public String listMatchingTaskResponse(TaskList tasks) throws ChatException {
        if (tasks.getSize() == 0) {
            return "There are currently no tasks in your list that matches the keyword.";
        }
        String output = "Here are the matching tasks in your list:";
        for (int i = 1; i < tasks.getSize() + 1; i++) {
            output = output.concat(System.lineSeparator() + i + ". " + tasks.getTask(i).toString());
        }
        return output;
    }

    /**
     * Response when task has been added to tasklist.
     * @param task Task to be added.
     * @param tasks Tasklist to add to.
     * @return Response to user as String.
     */
    public String addTaskResponse(Task task, TaskList tasks) {
        return "Got it. I've added this task:\n  " + task
                + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Response when task has been deleted from tasklist.
     * @param task Task to be deleted.
     * @param tasks Tasklist to delete from.
     * @return Response to user as String.
     */
    public String deleteTaskResponse(Task task, TaskList tasks) {
        return "Noted. I've removed this task:\n" + task
                + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Response when task has been marked done.
     * @param task Task to be marked.
     * @return Response when task has been marked done.
     */
    public String markDoneResponse(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Response when task has been marked undone.
     * @param task Task to be marked.
     * @return Response when task has been marked undone.
     */
    public String markUndoneResponse(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Response when user exits the chat.
     * @return Response when user exits the chat.
     */
    public String exitResponse() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Response when there is an error.
     * @param e The error to be shown.
     * @return Response when there is an error.
     */
    public String showLoadingError(ChatException e) {
        return e.getMessage();
    }

    /**
     * Response to invalid user input.
     * @return Response to invalid user input.
     */
    public String prompt() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-( \n"
                + "I can understand the following commands: \n"
                + "1. todo + description of task\n"
                + "     eg. todo take a nap\n"
                + "2. deadline + description of task + /by date (yyyy-mm-dd)\n"
                + "     eg. deadline English essay /by 2023-10-12\n"
                + "3. event + description of task + /from time + /to time\n"
                + "     eg. event charity run /from 4pm /to 6pm\n"
                + "4. list\n"
                + "5. mark + task number\n"
                + "     eg. mark 3\n"
                + "6. unmark + task number\n"
                + "     eg. unmark 2"
                + "7. delete + task number\n"
                + "     eg. delete 1"
                + "8. bye";
    }
}
