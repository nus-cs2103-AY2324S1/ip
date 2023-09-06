import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Main class for the duke application.
 */

public class Duke {
//    private ScrollPane scrollPane;
//    private VBox dialogContainer;
//    private TextField userInput;
//    private Button sendButton;
//    private Scene scene;
    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Image user = new Image(this.getClass().getResourceAsStream("images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("images/DaDuke.png"));

    public Duke() {
        //temporary to run launcher;
    }
    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }
//    @Override public void start(Stage stage) {
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//        userInput.setPrefWidth(325.0);
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//        mainLayout.setPrefSize(400.0, 600.0);
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//        AnchorPane.setLeftAnchor(userInput, 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        scene = new Scene(mainLayout);
//
//        stage.setScene(scene);
//        stage.show();
//
//        //step 3: add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//        userInput.setOnAction((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));
//
//        //Part 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//
//    }
//
//     * Iteration 2:
//     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
//     * the dialog container. Clears the user input after processing.
//
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, new ImageView(user)),
//                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
//        );
//        userInput.clear();
//    }
//
//
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

//    /**
//     * Iteration 1:
//     * Creates a label with the specified text and adds it to the dialog container.
//     * @param text String containing text to add
//     * @return a label with the specified text that has word wrap enabled.
//     */
//    private Label getDialogLabel(String text) {
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//
//        return textToAdd;
//    }
//
//
//    public static void main(String[] args) throws IOException {
//        new Duke("./data/duke.txt").run();
//    }

    /**
     * Main driver code for duke class.
     */
    public void run() throws IOException {
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            String str = bf.readLine();
            String command = parser.parseCommand(str);
            //regex detect all space
            if (command.equals("list")) {
                ui.printTaskList(tasks);
            } else if (command.equals("mark")) {
                int index = parser.parseToIndex();
                Task curr = tasks.getTask(index);
                curr.markAsDone();
                storage.store(tasks);
                ui.printMark(curr, index);
            } else if (command.equals("unmark")) {
                int index = parser.parseToIndex();
                Task curr = tasks.getTask(index);
                curr.markAsNotDone();
                storage.store(tasks);
                ui.printUnmark(curr, index);
            } else if (command.equals("bye")) {
                ui.printGoodbyeMessage();
                isExit = true;
            } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                Task curr = parser.parseToTask();
                if (curr == null) {
                    continue;
                }
                tasks.addTask(curr);
                storage.store(tasks);
                ui.printAddTask(curr, tasks.getSize());
            } else if (command.equals("delete")) {
                int index = parser.parseToIndex();
                Task curr = tasks.getTask(index);
                tasks.deleteTask(index);
                storage.store(tasks);
                ui.printDelete(curr, tasks.getSize());
            } else if (command.equals("find")) {
                String query = parser.parseQuery();
                ui.printQueryResult(tasks.searchTask(query));
            } else {
                //nothing found
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

    }
}
