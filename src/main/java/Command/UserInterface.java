package command;

import java.util.ArrayList;

import task.Task;

/**
 * The User interface class deals with common print statements that chatbot uses.
 */
public class UserInterface {

    /**
     * Show welcome message.
     *
     * @return the string
     */
    public String showWelcomeMessage() {
        StringBuilder result = new StringBuilder();
        result.append("Hello! I am Nila\n" + "What can I do for you?\n").append("\n");
        return result.toString();
    }

    /**
     * Show error.
     *
     * @param errorMessage the error message
     * @return the string
     */
    public String showError(String errorMessage) {
        return "Error: " + errorMessage;
    }

    /**
     * Show task list.
     *
     * @param taskList the task list
     * @return the string
     */
    public String showTaskList(ArrayList<Task> taskList) {
        StringBuilder result = new StringBuilder();
        result.append("Here are your tasks:\n");
        for (int i = 0; i < taskList.size(); i++) {
            result.append((i + 1)).append(". ").append(taskList.get(i).getStatusIcon()).append("\n");
        }
        result.append("\n");
        return result.toString();
    }


    /**
     * To show matching results.
     *
     * @param taskList the task list
     * @return the string
     */
    public String showFindTasks(ArrayList<Task> taskList) {
        StringBuilder result = new StringBuilder();
        if (taskList.size() == 0) {
            result.append("No matching tasks found.\n");
            result.append("\n");
            return result.toString();
        }
        result.append("Here are the matching tasks:\n");
        for (int i = 0; i < taskList.size(); i++) {
            result.append((i + 1)).append(". ").append(taskList.get(i).getStatusIcon()).append("\n");
        }
        result.append("\n");
        return result.toString();
    }

    /**
     * Show task updated message string.
     *
     * @param taskList the task list
     * @return the string
     */
    public String showTaskUpdatedMessage(ArrayList<Task> taskList, int index) {
        StringBuilder taskUpdatedMessage = new StringBuilder();
        taskUpdatedMessage.append("Got it! I've updated the task " + index + " to\n");
        taskUpdatedMessage.append(taskList.get(index).getStatusIcon()).append("\n");
        taskUpdatedMessage.append("\n");
        return taskUpdatedMessage.toString();
    }

    /**
     * Show task added message string.
     *
     * @param taskList the task list
     * @return the string
     */
    public String showTaskAddedMessage(ArrayList<Task> taskList) {
        StringBuilder taskAddedMessage = new StringBuilder();
        taskAddedMessage.append("Got it! I've added the task to your list.\n");
        taskAddedMessage.append(this.showCurrentStatus(taskList));
        taskAddedMessage.append("\n");
        return taskAddedMessage.toString();
    }

    /**
     * Show current status.
     *
     * @param taskList the task list
     * @return the string
     */
    public String showCurrentStatus(ArrayList<Task> taskList) {
        String result = "\n Now you have " + taskList.size() + " tasks in the list.\n";
        return taskList.get(taskList.size() - 1).getStatusIcon() + result;
    }

    /**
     * Show task deleted message.
     *
     * @param taskList the task list
     * @return the string
     */
    public String showTaskDeletedMessage(ArrayList<Task> taskList) {
        StringBuilder result = new StringBuilder();
        result.append("Noted! I've removed the task from your list.\n");
        result.append("\nNow you have ").append(taskList.size()).append(" tasks in your list\n").append("\n");
        return result.toString();
    }

    /**
     * Show task marked message.
     *
     * @param taskList the task list
     * @return the string
     */
    public String showTaskMarkedMessage(ArrayList<Task> taskList, int index) {
        StringBuilder result = new StringBuilder();
        result.append("Nice! I've marked this task as done.\n");
        result.append(taskList.get(index).getStatusIcon()).append("\n");
        result.append("\n");
        return result.toString();
    }

    /**
     * Show task unmarked message.
     *
     * @param taskList the task list
     * @return the string
     */
    public String showTaskUnmarkedMessage(ArrayList<Task> taskList, int index) {
        StringBuilder result = new StringBuilder();
        result.append(" OK, I've umarked this task.\n");
        result.append(taskList.get(index).getStatusIcon()).append("\n");
        result.append("\n");
        return result.toString();
    }

    /**
     * Show unknown command message.
     *
     * @return the string
     */
    public String showUnknownCommandMessage() {
        return "Sorry, I don't understand that command.\n"
                + "You can try the following commands: \n"
                + "'todo', 'deadline', 'event' for adding tasks\n"
                + "'mark', 'unmark', 'delete', 'update' to make changes to task list\n"
                + "'list' to display the task list\n"
                + "'bye' to close the task manager.\n";
    }


    /**
     * Show goodbye message.
     *
     * @return the string
     */
    public String showGoodbyeMessage() {
        StringBuilder result = new StringBuilder();
        result.append("Bye. Hope to see you again soon!\n").append("\n");
        return result.toString();
    }
}
