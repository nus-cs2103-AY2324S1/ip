package com.mimi.ui;

import com.mimi.main.DataCallback;
import com.mimi.tasks.Task;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * A class that is responsible for the user interface.
 * @author Yuheng
 */
public class Ui {
    private final DataCallback viewModel;
    private Stage window;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInputField;
    private Button sendButton;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image mimi = new Image(this.getClass().getResourceAsStream("/images/Mimi.jpg"));

    /**
     * Creates a new instance of the Ui.
     * @param window the Stage for the program to run in.
     * @param viewModel an implemented class for DataCallBack to receive the aata from the ui.
     */
    public Ui(Stage window, DataCallback viewModel) {
        this.window = window;
        this.viewModel = viewModel;
    }


    /**
     * Displays the given string on the stage.
     * @param s The input string.
     */
    public void showResponse(String s) {
        Label mimiText = getDialogLabel(s);
        dialogContainer.getChildren().add(DialogBox.mimiDialogBox(mimiText,
                new ImageView(this.mimi)));
    }

    /**
     * Displays a message when there is a loading error.
     */
    public void showLoadingError() {
        showResponse("Failed to load... Please try again!");
    }

    /**
     * Displays a welcome message when the program starts up.
     */
    public void welcomeMessage() {
        this.showResponse("Meow! I'm Mimi.\n" + "What can I do for you?");
    }

    /**
     * Displays a message when the user exits.
     */
    public void exitMessage() {
        this.showResponse("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a message when the task given is invalid.
     */
    public void invalidTaskMessage() {
        showResponse("OOPS!!! I'm sorry but I don't know what that means :-(");
    }

    /**
     * Displays a message when the user gives a mark command without specifying the task number.
     */
    public void incompleteMarkCommand() {
        showResponse("Sorry, you must specify a task number to be marked!");
    }

    /**
     * Displays a message when the user gives an un-mark command without specifying the task number.
     */
    public void incompleteUnmarkCommand() {
        showResponse("Sorry, you must specify a task number to be unmarked!");
    }

    /**
     * Displays a message when the user gives a delete command without specifying the task number.
     */
    public void incompleteDeleteCommand() {
        showResponse("Sorry, you must specify a task number to be deleted!");
    }

    /**
     * Displays a message when the user gives a deadline command without specifying the
     * deadline time.
     */
    public void incompleteDeadlineCommand() {
        showResponse("Sorry, you must specify a time for when the deadline is due!");
    }

    /**
     * Displays a message when the user gives the wrong time format in the input.
     */
    public void wrongTimeFormat() {
        showResponse("OOPS!! Looks like your time format is wrong, make sure to use "
                + "this format: DD/MM/YYYY HHmm.\nExample is 30/05/2023 2100.");
    }

    /**
     * Displays a message when the user gives an event command without specifying the
     * start or end time.
     */
    public void incompleteEventCommand() {
        showResponse("Sorry, you must specify a start time and an end time for the event!\n");
    }

    /**
     * Displays a confirmation message when a task is added successfully.
     * @param task The task added to the storage.
     * @param size The size of the storage containing previous tasks.
     */
    public void addTaskMessage(Task task, int size) {
        showResponse(String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                task.toString(), size));
    }

    /**
     * Displays every task previously stored.
     * @param index the index of the task in the storage.
     * @param task the task to be displayed.
     */
    public String listTask(int index, Task task) {
        return String.format("%d. %s",
                index, task.toString()) + "\n";
    }

    /**
     * Displays a message when the user tries to access a task in storage that does not
     * yet exist.
     */
    public void markUnmarkDeleteWrongTask() {
        showResponse("OOPS!!! Such a task does not exist!");
    }

    /**
     * Displays a message when the user tries to mark a task that has already been completed.
     */
    public void taskAlreadyMarkedAsDone() {
        showResponse("That task has already been marked as done!");
    }

    /**
     * Displays a message to confirm that a task is marked as done successfully.
     * @param task the task to be marked as done.
     */
    public void markTask(Task task) {
        showResponse(String.format(
                "Nice! I've marked this task as done: %s",
                task.toString()
        ));
    }

    /**
     * Displays a message when the user tries to un-mark a task that is not complete.
     */
    public void taskAlreadyUnmarked() {
        showResponse("That task has already been marked as not done!");
    }

    /**
     * Displays a message when the user successfully marks a task as not done.
     * @param task the task to be un-marked.
     */
    public void unmarkTask(Task task) {
        showResponse(
                String.format(
                        "Ok, I've marked this task as not done yet:\n%s",
                        task.toString()
                )
        );
    }

    /**
     * Displays a message when the user successfully deletes a task.
     * @param task the task to be deleted.
     */
    public void deleteTask(Task task) {
        showResponse(String.format("Noted. I've removed this task:\n%s", task.toString()));
    }

    /**
     * Displays a message when there is an error loading data from the hard disk.
     */
    public void unableToLoadFromMemory() {
        showResponse("Error while initialising: unable to load from memory.");
    }

    /**
     * Displays a message when there is an error writing data into the hard disk.
     */
    public void errorWhenUpdatingFile() {
        showResponse("Error when updating file.");
    }

    /**
     * Displays the task that matches the description given by the user.
     * @param task the task displayed
     * @param index the count of the task number being displayed.
     */
    public String returnSearchTerm(Task task, int index) {

        if (index == 1) {
            return "Here are the matching tasks in your list:\n"
                    + String.format(
                    "%d. %s", index, task.toString())
                    + "\n";
        }

        return String.format(
                "%d. %s", index, task.toString()) + "\n";
    }

    /**
     * Displays a message when there are no results that matches the user's query.
     */
    public void noSearchResults() {
        showResponse("OOPS!!! There are no search results for your query.");
    }

    /**
     * Displays a message when the user tries to search for tasks without
     * giving a search term.
     */
    public void incompleteSearchCommand() {
        showResponse("You must enter a search term!");
    }

    /**
     * Initialises the Ui.
     */
    public void initialise() {
        this.window.setTitle("Mimi");

        window.setResizable(false);
        window.setMinHeight(600.0);
        window.setMinWidth(400.0);

        this.scrollPane = new ScrollPane();

        this.dialogContainer = new VBox();
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        this.scrollPane.setContent(dialogContainer);
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        this.userInputField = new TextField();
        this.userInputField.setMinWidth(350);
        this.sendButton = new Button("SEND");

        this.configureButton();
        this.configureUserInputField();

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInputField, sendButton);

        mainLayout.setPrefSize(400.0, 600.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInputField , 1.0);
        AnchorPane.setBottomAnchor(userInputField, 1.0);



        window.setScene(new Scene(mainLayout));
        window.show();

        this.welcomeMessage();
    }

    private void configureUserInputField() {
        this.userInputField.setOnAction(
                e -> {
                    if (!userInputField.getText().equals("")) {
                        Label userText = getDialogLabel(userInputField.getText());
                        dialogContainer.getChildren().add(DialogBox.userDialogBox(userText,
                                new ImageView(this.user)));

                        viewModel.onDataReceived(userInputField.getText());
                        userInputField.clear();
                    } else { }
                });
    }

    private void configureButton() {
        this.sendButton.setOnMouseClicked(
                e -> {
                    if (!userInputField.getText().equals("")) {
                        Label userText = getDialogLabel(userInputField.getText());
                        dialogContainer.getChildren().add(DialogBox.userDialogBox(userText,
                                new ImageView(this.user)));

                        viewModel.onDataReceived(userInputField.getText());
                        userInputField.clear();
                    } else { }
                });
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

}
