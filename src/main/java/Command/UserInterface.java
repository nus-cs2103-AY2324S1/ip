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
        result.append(this.showCommandLine());
        result.append("Hello! I am Nila\n" + "What can I do for you?\n");
        result.append(this.showCommandLine());
        return result.toString();
    }

    /**
     * Show command line.
     *
     * @return the string
     */
    public String showCommandLine() {
        return "- - - - - - - - - - - - - - - - - - - - - - - - -\n";
    }

    /**
     * Show message.
     *
     * @param message the message
     * @return the string
     */
    public String showMessage(String message) {
        return message;
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
        result.append(this.showCommandLine());
        result.append("Here are your tasks:\n");
        for (int i = 0; i < taskList.size(); i++) {
            result.append((i + 1)).append(". ").append(taskList.get(i)).append("\n");
        }
        result.append(this.showCommandLine());
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
        result.append(this.showCommandLine());
        result.append("Here are the matching tasks:\n");
        for (int i = 0; i < taskList.size(); i++) {
            result.append((i + 1)).append(". ").append(taskList.get(i)).append("\n");
        }
        result.append(this.showCommandLine());
        return result.toString();
    }

    /**
     * Show task added message string.
     *
     * @param taskList the task list
     * @return the string
     */
    public String showTaskAddedMessage(ArrayList<Task> taskList) {
        StringBuilder taskAddedMessage = new StringBuilder();
        taskAddedMessage.append(this.showCommandLine());
        taskAddedMessage.append("Got it! I've added the task to your list.\n");
        taskAddedMessage.append(this.showCurrentStatus(taskList));
        taskAddedMessage.append(this.showCommandLine());
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
        result.append(this.showCommandLine());
        result.append("Noted! I've removed the task from your list.\n");
        result.append(this.showCommandLine());
        return result.toString();
    }

    /**
     * Show task marked message.
     *
     * @param taskList the task list
     * @return the string
     */
    public String showTaskMarkedMessage(ArrayList<Task> taskList) {
        StringBuilder result = new StringBuilder();
        result.append(this.showCommandLine());
        result.append("Nice! I've marked this task as done.\n");
        result.append(this.showCurrentStatus(taskList));
        result.append(this.showCommandLine());
        return result.toString();
    }

    /**
     * Show task unmarked message.
     *
     * @param taskList the task list
     * @return the string
     */
    public String showTaskUnmarkedMessage(ArrayList<Task> taskList) {
        StringBuilder result = new StringBuilder();
        result.append(this.showCommandLine());
        result.append(" OK, I've marked this task as not done yet.\n");
        result.append(this.showCurrentStatus(taskList));
        result.append(this.showCommandLine());
        return result.toString();
    }

    /**
     * Show unknown command message.
     *
     * @return the string
     */
    public String showUnknownCommandMessage() {
        return "Sorry, I don't understand that command.\n";
    }

    /**
     * Show loading error.
     *
     * @return the string
     */
    public String showLoadingError() {
        return "Error loading tasks. Starting with an empty list.\n";
    }

    /**
     * Show goodbye message.
     *
     * @return the string
     */
    public String showGoodbyeMessage() {
        StringBuilder result = new StringBuilder();
        result.append(this.showCommandLine());
        result.append("Bye. Hope to see you again soon!\n");
        result.append(this.showCommandLine());
        return result.toString();
    }
}
