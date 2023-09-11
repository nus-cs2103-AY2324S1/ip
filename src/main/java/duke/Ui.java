package duke;

import duke.Exception.DukeException;
import duke.task.Task;

public class Ui {
    private StringBuilder message;
    public Ui() {
        this.message = new StringBuilder();
    }


    /**
     * Prints the welcome message.
     */
    public static String printWelcomeMessage() {
        StringBuilder message = new StringBuilder();
        message.append("Hey there amigo, excited to meet you! I'm Buddy, your friendly chat companion!\n");
        message.append("What can I do for you?\n");
        return message.toString();
    }

    /**
     * Prints the goodbye message.
     */
    public String printGoodByeMessage() {
        append("Bye! Hope to see you again soon!\n");
        return message.toString();
    }

    /**
     * Prints the task as being marked as done.
     * @param index the index of the task that is to be marked.
     * @param tasks the TaskList being used.
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
     */

    public String printMarkTasksAsNotDone(int index, TaskList tasks) {
        append("Ok! I've marked this task as not done yet:\n");
        StringBuilder str = message.append(index + 1).append(".").append(tasks.getTask(index).toString()).append("\n");
        return message.toString();

    }
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
            e.printMessage();
        }
        return message.toString();
    }

    /**
     * Prints the TaskList.
     * @param tasks the TaskList being used.
     * @throws DukeException throws a duke exception.
     */
    public String printListMessage(TaskList tasks) throws DukeException {
        append(printTasks(tasks));
        return message.toString();
    }

    public String showError(DukeException e) {
        return e.getMessage();
    }

    /**
     * Prints message that the task is added.
     */

    public String printAddedTask() {
        return "No problem! I have added this task:";
    }

    /**
     * Prints the horizontal line.
     */


    /**
     * Prints that a task has been deleted.
     * @param tasks the TaskList being used.
     * @param element the Task that is to be deleted.
     * @throws DukeException throws a duke exception.
     */
    public String printDeleteTasks(TaskList tasks, Task element) throws DukeException {
        append("Okie I've removed this task:\n" + element.toString() + "\n");
        append("Now you have " + tasks.getSize() + " tasks in the list.\n");
        return message.toString();
    }

    /**
     * Prints that a task has been added.
     * @param tasks the TaskList being used.
     * @param task the task that is to be added.
     */

    public String printAddTaskToList(TaskList tasks, Task task) {
        append("No problem! I have added this task:\n" + task.toString() + "\n");
        append("Now you have " + tasks.getSize() + " tasks in the list.\n");
        return message.toString();
    }

    /**
     * * prints what tasks have been found.
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
    public String printNoFoundTask(String keyword) {
        append("No tasks found containing the keyword: " + keyword);
        return message.toString();
    }
    public void resetOutput() {
        message.setLength(0);
    }

    public String getOutput() {
        return message.toString();
    }

    public void append(String str) {
        message.append(str);
    }

}
