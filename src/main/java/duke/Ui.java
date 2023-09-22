package duke;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;


import java.util.Scanner;



public class Ui {
    private final Scanner scanner;

    private VBox dialogContainer;
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/johnnythesnake.png"));
    //takes in user input
    public Ui() {
        scanner = new Scanner(System.in);
    }
    // ask for user input and finds the command of the user
    public String getUserInput() {
        System.out.print("Enter a command: ");
        return scanner.nextLine().trim();
    }

    // default welcome message
    public void showWelcomeMessage() {
        String welcomeMessage = "Hello! I'm JohnnytheSnake, your personal chatbot. How can I assist you today?";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMessage, dukeImage)
        );
    }

    //Exit message when user enters bye
    public String showExitMessage() {
        return "Goodbye! Have a great day!";
    }

    // shows the user the active tasks when they ask for it
    public String showTaskList(TaskList tasks) {
        return tasks.listOfTasks();
    }

    // mark task as done
    public void showMarkedAsDone(int taskNumber) {
        System.out.println("Task " + (taskNumber + 1) + " marked as done.");
    }

    // unmark task
    public void showUnmarked(int taskNumber) {
        System.out.println("Task " + (taskNumber + 1) + " unmarked.");
    }
    // shows the tasks added
    public void showTaskAdded() {
        System.out.println("Task added.");
    }

    // shows which taks has been deleted
    public void showTaskDeleted(int taskNumber) {
        System.out.println("Task " + (taskNumber + 1) + " deleted.");
    }

    // shows the user an error message depending on what he did wrong
    public String showError(String errorMessage) {
        return "Error: " + errorMessage;
    }

    // if issues arise from loading tasks, this message is shown
    public void showLoadingError() {
        System.out.println("Error loading tasks from storage.");
    }

    public void addToDialog(String message) {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(message, dukeImage));
    }

    public void closeScanner() {
        scanner.close();
    }
}

