package command;

import java.util.ArrayList;
import task.Task;

/**
 * The User interface class deals with common print statements that chatbot uses.
 */

public class UserInterface {

    /**
     * Show welcome message.
     */
    public String showWelcomeMessage() {
        return "- - - - - - - - - - - - - - - - - - - - - - - - -\n" + "Hello! I am Nila\n" +
                "What can I do for you?\n" + "- - - - - - - - - - - - - - - - - - - - - - - - -";
    }

    /**
     * Show command line.
     */

    public String showCommandLine(){
        return "- - - - - - - - - - - - - - - - - - - - - - - - -\n";
    }

    /**
     * Show message.
     *
     * @param message the message
     */

    public String showMessage(String message) {
        return message;
    }

    /**
     * Show error.
     *
     * @param errorMessage the error message
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
        StringBuilder result = new StringBuilder("Here are your tasks:\n");
        for (int i = 0; i < taskList.size(); i++) {
            result.append((i + 1)).append(". ").append(taskList.get(i)).append("\n");
        }
        return result.toString();
    }
    /**
     * To show matching results.
     */


    public String showFindTasks(ArrayList<Task> taskList) {
        StringBuilder result = new StringBuilder("Here are the matching tasks:\n");
        for (int i = 0; i < taskList.size(); i++) {
            result.append((i + 1)).append(". ").append(taskList.get(i)).append("\n");
        }
        return result.toString();
    }

    public String showTaskAddedMessage() {
        return "Got it! I've added the task to your list.\n";
    }

    /**
     * Show current status.
     *
     * @param taskList the task list
     */
    public String showCurrentStatus(ArrayList<Task> taskList){
        return taskList.get(taskList.size() - 1).getStatusIcon() +
            "\n Now you have " + taskList.size() +" tasks in the list.\n";
    }

    /**
     * Show task deleted message.
     */

    public String showTaskDeletedMessage() {
        return "Noted! I've removed the task from your list.\n";
    }

    /**
     * Show task marked message.
     */
    public String showTaskMarkedMessage() {
        return "Nice! I've marked this task as done.\n";
    }

    /**
     * Show task unmarked message.
     */
    public String showTaskUnmarkedMessage() {
        return " OK, I've marked this task as not done yet.\n";
    }

    /**
     * Show unknown command message.
     */

    public String showUnknownCommandMessage() {
        return "Sorry, I don't understand that command.\n";
    }

    /**
     * Show loading error.
     */

    public String showLoadingError() {
        return "Error loading tasks. Starting with an empty list.\n";
    }

    /**
     * Show goodbye message.
     */

    public String showGoodbyeMessage() {
        return "\nBye. Hope to see you again soon!\n";
    }

}
