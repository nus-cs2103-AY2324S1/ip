package duke;

import duke.Exception.DukeException;
import duke.task.Task;

/**
 * Class that deals with the UI of the chatbot.
 */
public class Ui {
    private StringBuilder message;
    public Ui() {
        this.message = new StringBuilder();
    }


    /**
     * Prints the welcome message.
     * @return String representation of the message.
     */
    public static String printWelcomeMessage() {
        StringBuilder message = new StringBuilder();
        message.append("Hey there amigo, excited to meet you! I'm Buddy, your friendly chat companion!\n");
        message.append("What can I do for you?\n");
        return message.toString();
    }

    /**
     * Prints the goodbye message.
     * @return String representation of the message.
     */
    public String printGoodByeMessage() {
        append("Bye! Hope to see you again soon!\n");
        return message.toString();
    }

    /**
     * Prints the task as being marked as done.
     * @param index the index of the task that is to be marked.
     * @param tasks the TaskList being used.
     * @return String representation of the message.
     */
    public String printMarkTasksAsDone(int index, TaskList tasks) {
        append("Great! I've marked this task as done:\n");
        StringBuilder str = message.append(index + 1).append(".").append(tasks.getTask(index).toString()).append("\n");
        return message.toString();
    }

    /**
     * Prints the task as being marked as not done yet.
     * @param index the index of the task is to be marked as not done.
     * @param tasks the TaskList being used.
     * @return String representation of the message.
     */

    public String printMarkTasksAsNotDone(int index, TaskList tasks) {
        append("Ok! I've marked this task as not done yet:\n");
        StringBuilder str = message.append(index + 1).append(".").append(tasks.getTask(index).toString()).append("\n");
        return message.toString();

    }

    /**
     * Returns the tasks in the tasklist.
     * @param tasks the tasklist that will be printed.
     * @return the string representation of the list.
     */
    public String printTasks(TaskList tasks) {
        StringBuilder message = new StringBuilder();
        try {
            message.append("Here are the tasks in your list:").append("\n");;
            if (tasks.getSize() == 0) {
                throw new DukeException("Seems like you have no tasks at the moment :) ");

            }
            for (int i = 1; i <= tasks.getSize(); i++) {
                message.append(i + ". " + tasks.getTask(i - 1).toString()).append("\n");
            }
        } catch (DukeException e) {
            return showError(e);
        }
        return message.toString();
    }

    /**
     * Prints the TaskList.
     * @param tasks the TaskList being used.
     * @return String representation of the message.
     * @throws DukeException throws a duke exception.
     */
    public String printListMessage(TaskList tasks) throws DukeException {
        append(printTasks(tasks));
        return message.toString();
    }

    /**
     * Shows the error.
     * @param e the DukeException being thrown.
     * @return String representation of the message.
     */
    public String showError(DukeException e) {
        return e.getMessage();
    }
    /**
     * Prints that a task has been deleted.
     * @param tasks the TaskList being used.
     * @param element the Task that is to be deleted.
     * @return String representation of the message.
     * @throws DukeException throws a duke exception.
     */
    public String printDeleteTasks(TaskList tasks, Task element) throws DukeException {
        append("Okie I've removed this task:\n" + element.toString() + "\n");
        append("Now you have " + tasks.getSize() + " tasks in the list.\n");
        return message.toString();
    }

    /**
     * Prints that the tasks have been deleted.
     * @return String representation of the message.
     */
    public String printDeleteManyTasks() {
        append("Okie I've removed these tasks!");
        return message.toString();
    }

    /**
     * Prints that a task has been added.
     * @param tasks the TaskList being used.
     * @param task the task that is to be added.
     * @return String representation of the message.
     */

    public String printAddTaskToList(TaskList tasks, Task task) {
        append("No problem! I have added this task:\n" + task.toString() + "\n");
        append("Now you have " + tasks.getSize() + " tasks in the list.\n");
        return message.toString();
    }

    /**
     ** Prints what tasks have been found.
     * @param foundTasks the array used to store the tasks that match.
     */
    public String printFindTask(TaskList foundTasks) {
        append("Sure, I can do that! What are buddies for after all?\n");
        append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < foundTasks.getSize(); i++) {
            Task task = foundTasks.getTask(i);
            message.append(i + 1).append(". ").append(task.toString()).append("\n");
        }
        return message.toString();
    }

    /**
     * Returns the tasks that match the keyword.
     * @param keyword the keyword the user is trying to find.
     * @return the string representation of the tasks that matches the keyword.
     */
    public String printNoFoundTask(String keyword) {
        append("No tasks found containing the keyword: " + keyword);
        return message.toString();
    }

    /**
     * Resets output.
     */
    public void resetOutput() {
        message.setLength(0);
    }

    /**
     * Returns output.
     * @return String output.
     */
    public String getOutput() {
        return message.toString();
    }

    /**
     * Appends output to message.
     * @param str the string that will be appended.
     */
    public void append(String str) {
        message.append(str);
    }

    /**
     * Returns the help message.
     * @return String representation of the help message.
     */
    public String printHelpMessage() {
        append("Sure buddy I'm always here for you! These are the commands I have and what they do! \n");
        append("1. todo: Type this to add a todo to your list!\n");
        append("2. deadline: Type this to add a deadline to your list! "
                + "(ps remember to add when it is due by adding a /by)\n");
        append("3. event: Type this to add an event to your list!) "
                + "(ps remember to add when it starts and ends by adding a /from and /to)\n");
        append("4. list: Type this to view your tasks!\n");
        append("5. delete: Type this to delete a task. Add the task number as well!\n");
        append("6. mark: Type this to mark a task on the list. Add the task number as well!\n");
        append("7. unmark: Type this to unmark a task on the list. Add the task number as well!\n");
        append("8. find: Type this to find a task with the keyword you are searching for\n");
        append("9. massDelete: Type this to mass delete 2 or more tasks at once! eg: massDelete 1 2\n");
        append("10. bye: Type this and I will say goodbye :(\n");
        return message.toString();
    }

}
