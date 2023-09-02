package Command;

import Task.Task;
import java.util.ArrayList;

/**
 * The User interface class deals with common print statements that chatbot uses.
 */
public class UserInterface {

    /**
     * Show welcome message.
     */
    public void showWelcomeMessage() {
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Hello! I am Nila");
        System.out.println("What can I do for you?");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
    }

    /**
     * Show command line.
     */

    public void showCommandLine(){
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
    }

    /**
     * Show message.
     *
     * @param message the message
     */

    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Show error.
     *
     * @param errorMessage the error message
     */

    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    /**
     * Show task list.
     *
     * @param taskList the task list
     */

    public void showTaskList(ArrayList<Task> taskList) {
        System.out.println("Here are your tasks:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }

    /**
     * Show task added message.
     */

    public void showTaskAddedMessage() {
        System.out.println("Got it! I've added the task to your list.");
    }

    /**
     * Show current status.
     *
     * @param taskList the task list
     */
    public void showCurrentStatus(ArrayList<Task> taskList){
        System.out.println(taskList.get(taskList.size() - 1).getStatusIcon());
        System.out.println(" Now you have " + taskList.size() +" tasks in the list.");
    }

    /**
     * Show task deleted message.
     */

    public void showTaskDeletedMessage() {
        System.out.println("Noted! I've removed the task from your list.");
    }

    /**
     * Show task marked message.
     */
    public void showTaskMarkedMessage() {
        System.out.println("Nice! I've marked this task as done.");
    }

    /**
     * Show task unmarked message.
     */
    public void showTaskUnmarkedMessage() {
        System.out.println(" OK, I've marked this task as not done yet.");
    }

    /**
     * Show unknown command message.
     */

    public void showUnknownCommandMessage() {
        System.out.println("Sorry, I don't understand that command.");
    }

    /**
     * Show loading error.
     */

    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting with an empty list.");
    }

    /**
     * Show goodbye message.
     */

    public void showGoodbyeMessage() {
        System.out.println("\nBye. Hope to see you again soon!");
    }

}
