package duke;

import java.util.ArrayList;
import java.util.function.Function;

import duke.command.Command;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents a Ui class that deals with the interactions with the user.
 */
public class Ui {
    private final String lineSeparator = "____________________________________________________________";
    GobbleChatContainer dialogContainer;
    TextField userInput;
    ScrollPane scrollPane;
    GobbleAnchorPane mainLayout;
    Button sendButton;

    public void initialiseStage(Stage stage) {
        stage.setTitle("Gobble Gobble");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        stage.setScene(initialScene());
        stage.show();
    }

    public void layout() {

    }

    public Scene initialScene() {
        this.dialogContainer = new GobbleChatContainer();
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        this.userInput = new GobbleTextField();
        this.scrollPane = new GobbleScrollPane();
        this.mainLayout = new GobbleAnchorPane();
        this.sendButton = new GobbleButton("Send");


        scrollPane.setContent(dialogContainer);
        this.mainLayout.setElements(scrollPane, userInput, sendButton);

        // Make it so that the scroll pane is always at the bottom when dialogContainer's height changes.
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        return new Scene(mainLayout);
    }

    public void handleSubmit(Storage storage, TaskList taskList) {
        this.sendButton.setOnMouseClicked((event) -> {
            String input = userInput.getText();
            try {
                Command command = Parser.parse(input);
                command.execute(taskList, dialogContainer, storage);
            } catch (DukeException e) {
                System.out.println("ADASD");
                System.out.println(e.getMessage());
            }
            userInput.clear();
        });
    }

    /**
     * Prints the welcome message.
     */
    public void showWelcomeMessage() {
        String chatbotName = "Gobble Gobble";
        System.out.println(this.lineSeparator + "\n" + "Hello! I'm " + chatbotName + "\n"
                + "What can I do for you?" + "\n" + this.lineSeparator);
    }

    /**
     * Prints the goodbye message.
     */
    public void showByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the error message.
     *
     * @param message error message.
     */
    public void showError(String message) {
        System.out.println(lineSeparator + "\n" + message + "\n" + lineSeparator);
    }

    /**
     * Prints the task as marked.
     *
     * @param task task to be marked.
     */
    public void showMarkMessage(Task task) {
        System.out.println(this.lineSeparator + "\n" + "Nice! I've marked this task as done:" + "\n"
                + task + "\n" + this.lineSeparator);
    }

    /**
     * Prints the task as unmarked.
     *
     * @param task task to be unmarked.
     */
    public void showUnmarkMessage(Task task) {
        System.out.println(this.lineSeparator + "\n" + "OK, I've marked this task as not done yet:" + "\n"
                + task + "\n" + this.lineSeparator);
    }

    /**
     * Prints the task as deleted.
     *
     * @param task task to be deleted.
     * @param size size of the task list after deletion.
     */
    public void showDeleteMessage(Task task, int size) {
        System.out.println(this.lineSeparator + "\n" + "Noted. I've removed this task:" + "\n"
                + task.getDescription() + "\n" + "Now you have " + (size) + " tasks in the list." + "\n"
                + this.lineSeparator);
    }

    /**
     * Prints the task as added.
     *
     * @param task task to be added.
     * @param size size of the task list after addition.
     */
    public void showAddTaskMessage(Task task, int size) {
        System.out.println(this.lineSeparator + "\n" + "Got it. I've added this task:" + "\n"
                + task.toString() + "\n" + "Now you have " + (size) + " tasks in the list." + "\n"
                + this.lineSeparator);
    }

    /**
     * Prints the task list.
     *
     * @param taskList list of tasks.
     */
//    public void showTasks(TaskList taskList) {
//        System.out.println(this.lineSeparator);
//        System.out.println("Here are the tasks in your list:");
//        System.out.println(taskList);
//    }

    /**
     * Prints the result list.
     *
     * @param list list of tasks.
     */
    public String printResultList(ArrayList<Task> list) {
        String result;
        result = this.lineSeparator + "\n" + "Here are the matching tasks in your list:" + "\n";
        for (int i = 0; i < list.size(); i++) {
            result += (i + 1) + "." + list.get(i) + "\n";
        }
        result += this.lineSeparator;
        return result;
//        System.out.println(lineSeparator);
//        System.out.println("Here are the matching tasks in your list:");
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println((i + 1) + "." + list.get(i));
//        }
//        System.out.println(lineSeparator);
    }
}
