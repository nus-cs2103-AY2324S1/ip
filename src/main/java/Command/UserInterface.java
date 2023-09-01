package Command;

import Task.Task;
import java.util.ArrayList;

public class UserInterface {

    public void showWelcomeMessage() {
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Hello! I am Nila");
        System.out.println("What can I do for you?");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
    }

    //Method to display command line
    public void showCommandLine(){
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
    }
    // Method to display a message
    public void showMessage(String message) {
        System.out.println(message);
    }

    // Method to display an error message
    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    // Method to display the list of tasks
    public void showTaskList(ArrayList<Task> taskList) {
        System.out.println("Here are your tasks:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }

    // Method to display a task added message
    public void showTaskAddedMessage() {
        System.out.println("Got it! I've added the task to your list.");
    }

    public void showCurrentStatus(ArrayList<Task> taskList){
        System.out.println(taskList.get(taskList.size() - 1).getStatusIcon());
        System.out.println(" Now you have " + taskList.size() +" tasks in the list.");
    }

    // Method to display a task deleted message
    public void showTaskDeletedMessage() {
        System.out.println("Noted! I've removed the task from your list.");
    }

    public void showTaskMarkedMessage() {
        System.out.println("Nice! I've marked this task as done.");
    }

    public void showTaskUnmarkedMessage() {
        System.out.println(" OK, I've marked this task as not done yet.");
    }

    // Method to display an unknown command message
    public void showUnknownCommandMessage() {
        System.out.println("Sorry, I don't understand that command.");
    }

    // Method to show loading error message
    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting with an empty list.");
    }

    // Method to display a goodbye message
    public void showGoodbyeMessage() {
        System.out.println("\nBye. Hope to see you again soon!");
    }

}
