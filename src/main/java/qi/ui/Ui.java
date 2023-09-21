package qi.ui;

import qi.task.Task;
import qi.tasklist.TaskList;

/**
 * Represents the interactions of the chatbot with users.
 */
public class Ui {

    /**
     * Shows welcoming message.
     *
     * @return String message to users.
     */
    public String showWelcome() {
        return "Hello! I'm Qi\n"
            + "What can I do for you? \n"
                + "\nNot sure what to do?\n"
                + "Type: help";
    }

    /**
     * Shows the task added to the list.
     *
     * @param task Task added to the list.
     * @param list Current TaskList.
     * @return String message to users.
     */
    public String showTaskAdded(Task task, TaskList list) {
        return "Got it. I've added this task:\n"
            + " " + task + '\n'
            + "Now you have " + list.size() + " tasks in the list.";
    }

    /**
     * Shows the task removed from the list.
     *
     * @param taskId Id of the task removed.
     * @param list Current TaskList.
     * @return String message to users.
     */
    public String showTaskDeleted(int taskId, TaskList list) {
        return "Noted. I've removed this task:\n"
            + "  " + list.deleteTask(taskId) + '\n'
            + "Now you have " + list.size() + " tasks in the list.";
    }

    /**
     * Shows the task marked as done.
     *
     * @param taskId Id of the task marked as done.
     * @param list Current TaskList.
     * @return String message to users.
     */
    public String showTaskMarked(int taskId, TaskList list) {
        list.mark(taskId, true);
        return "Nice! I've marked this task as done:\n"
            + "  " + list.showTask(taskId);
    }

    /**
     * Shows the task unmarked.
     *
     * @param taskId Id of the task unmarked.
     * @param list Current TaskList.
     * @return String message to users.
     */
    public String showTaskUnmarked(int taskId, TaskList list) {
        list.mark(taskId, false);
        return "OK, I've marked this task as not done yet:\n"
            + "  " + list.showTask(taskId);
    }

    /**
     * Shows the content of the list.
     *
     * @param list Current TaskList.
     * @return String message to users.
     */
    public String showList(TaskList list) {
        return "Here are the tasks in your list:\n"
            + list;
    }

    /**
     * Shows the tasks in the list that match
     * a certain keyword.
     *
     * @param matchingTasks String representation of the matching tasks.
     * @return String message to users.
     */
    public String showMatching(String matchingTasks) {
        return "Here are the matching tasks in your list:\n"
            + matchingTasks;
    }


    /**
     * Shows a message that the input is invalid.
     *
     * @return String message to users.
     */
    public String showInvalid() {
        return "OOPS!!! I'm sorry, but I don't know what that means :<";
    }

    /**
     * Shows goodbye message.
     *
     * @return String message to users.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Shows message that the file cannot be loaded.
     *
     * @return String message to users.
     */
    public String showLoadingError() {
        return "Cannot load file!";
    }

    /**
     * Shows the guidance to users.
     *
     * @return String of the guide.
     */
    public String showGuidance() {
        String guide = "I am supposed to help you managing your tasks!\n"
                + "Here is the format of all the command you can use:\n"
                + "  - To add a todo task: todo TASK_DESCRIPTION\n"
                + "  - To add a deadline: deadline TASK_DESCRIPTION /by yyyy-mm-dd\n"
                + "  - To add an event: event TASK_DESCRIPTION /from START_TIME /to END_TIME\n"
                + "  - To mark a task as done: mark TASK_ID\n"
                + "  - To unmart a task: unmark TASK_ID\n"
                + "  - To delete a task: delete TASK_ID\n"
                + "  - To view all the task in your list: list\n"
                + "  - To exit the app: bye\n"
                + "That's all you need to know!\n"
                + "Hope you a great experience!";
        return guide;
    }
}
