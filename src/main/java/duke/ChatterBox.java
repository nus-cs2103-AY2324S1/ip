package duke;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.scene.layout.Region;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * An interactive digital task manager called ChatterBox.
 * This minimal chatbot can take in simple commands, remember
 * tasks even after termination, and manipulate them as per the 
 * user's requests. 
 */
public class ChatterBox extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Ui ui = new Ui();
    private TaskList tl = new TaskList();
    private Storage store = new Storage();

    private Image user = new Image(this.getClass().getResourceAsStream("/DaUser.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/DaDuke.jpg"));

    /**
     * Constructs an empty ChatterBox object.
     */
    public ChatterBox() throws IOException, DukeException {
        this(new Ui(), new TaskList(), new Storage());
        //this.run();
        ArrayList<Task> taskList = this.tl.getTaskList();
        this.store.fileToTaskList(this.tl);

    }

    /**
     * Constructs a ChatterBox object with all parameters specified.
     *
     * @param ui The supplied Ui object.
     * @param tl The supplied TaskList object.
     * @param store The supplied Storage object.
     */
    ChatterBox(Ui ui, TaskList tl, Storage store) throws IOException {
        this.ui = ui;
        this.tl = tl;
        this.store = store;
    }


    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("ChatterBox");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // more code to be added here later

        Label firstText = new Label(ui.startScreen());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(firstText, new ImageView(duke))
        );

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        String res = null;
        try {
            res = parseText(input);
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
        return "ChatterBox:" + res;
    }

    /**
     * Runs the actual program.
     *
     * @throws DukeException For various Chat-specific errors.
     * @throws IOException If FileIO presents any issues during runtime.
     */
    /*public void run() throws DukeException, IOException {

        ArrayList<Task> taskList = this.tl.getTaskList();
        this.store.fileToTaskList(this.tl);
        ui.startScreen();

    }*/

    public String parseText(String cmd) throws DukeException {

        Parser p = new Parser(cmd);
        String command = p.command();
        String fullLine = p.fullLine;
        String finalOutput = "";

        switch (command) {
        case "bye":
            finalOutput = ui.byeScreen();
            Platform.exit();
            break;

        case "list":
            finalOutput = ui.taskListPrinter(tl);
            break;

        case "mark":
            tl.mark(p.num());
            try {
                this.store.taskListToFile(tl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            finalOutput = ui.markPrinter(tl, p.num());

            break;

        case "unmark":
            tl.unmark(p.num());
            try {
                this.store.taskListToFile(tl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            finalOutput = ui.unmarkPrinter(tl, p.num());

            break;

        case "delete":
            Task tempDelete = this.tl.getTaskList().get(p.num());
            tl.remove(p.num());
            try {
                this.store.taskListToFile(tl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            finalOutput = ui.removedTaskScreen(tempDelete, tl.size());

            break;

        case "find":
            finalOutput = ui.findListPrinter(tl, p.word());

            break;

        default:

            if (command.equals("todo")) {

                ToDo tempToDo = p.parseTodo();
                tl.add(tempToDo);
                try {
                    this.store.taskListToFile(tl);
                } catch (IOException e) {
                    return new Ui().todoErrorString();
                }
                finalOutput = ui.addedTaskScreen(tempToDo, tl.size());

            } else if (command.equals("deadline")) {

                Deadline tempDeadline = null;
                try {
                    tempDeadline = p.parseDeadline();
                } catch (DukeException e) {
                    return new Ui().deadlineErrorString();
                }
                tl.add(tempDeadline);

                try {
                    this.store.taskListToFile(tl);
                } catch (IOException e) {
                    return new Ui().fileErrorString();
                }
                finalOutput = ui.addedTaskScreen(tempDeadline, this.tl.getTaskList().size());

            } else if (command.equals("event")) {

                Event tempEvent = p.parseEvent();
                tl.add(tempEvent);
                try {
                    this.store.taskListToFile(tl);
                } catch (IOException e) {
                    return new Ui().eventErrorString();
                }
                finalOutput = ui.addedTaskScreen(tempEvent, tl.size());

            } else {

                finalOutput = ui.linePrinter() + ui.unknownError();
                //throw new DukeException(ui.unknownError());
            }

            break;
        }

        return finalOutput;

    }



}
