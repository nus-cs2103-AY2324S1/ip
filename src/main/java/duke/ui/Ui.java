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
     * Response when tasks has been deleted from tasklist.
     * @param deletedList Tasks to be deleted.
     * @param tasks Tasklist to delete from.
     * @return Response to user as String.
     */
    public String deleteTaskResponse(TaskList deletedList, TaskList tasks) throws ChatException {
        String output = "Noted. I've removed the following task(s):";
        for (int i = deletedList.getSize(); i > 0; i--) {
            output = output.concat(System.lineSeparator() + deletedList.getTask(i).toString());
        }
        output = output.concat(System.lineSeparator() + "Now you have " + tasks.getSize() + " tasks in the list.");
        return output;
    }

    /**
     * Response when tasks has been marked done.
     * @param markList Tasks to be marked.
     * @return Response when task has been marked done.
     */
    public String markDoneResponse(TaskList markList) throws ChatException {
        String output = "Nice! I've marked the following task(s) as done:";
        for (int i = 1; i <= markList.getSize(); i++) {
            output = output.concat(System.lineSeparator() + markList.getTask(i).toString());
        }
        return output;
    }

    /**
     * Response when tasks has been marked undone.
     * @param unmarkList Tasks to be marked.
     * @return Response when task has been marked undone.
     */
    public String markUndoneResponse(TaskList unmarkList) throws ChatException {
        String output = "OK, I've marked the following task(s) as not done yet:";
        for (int i = 1; i <= unmarkList.getSize(); i++) {
            output = output.concat(System.lineSeparator() + unmarkList.getTask(i).toString());
        }
        return output;
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
                + "5. mark + task number(s) to be separated by commas and arranged in ascending order\n"
                + "     eg. mark 3, 4, 7\n"
                + "6. unmark + task number(s) to be separated by commas and arranged in ascending order\n"
                + "     eg. unmark 2, 5\n"
                + "7. delete + task number(s) to be separated by commas and arranged in ascending order\n"
                + "     eg. delete 1, 4, 8\n"
                + "8. bye";
    }
}
