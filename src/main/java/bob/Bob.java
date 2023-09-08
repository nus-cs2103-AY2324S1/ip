package bob;

/*import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;*/

import java.util.Scanner;

/**
 * Represents a bot that can record three types of tasks: todo, deadline and event, as well as
 * mark those tasks as done and delete
 */
public class Bob /*extends Application*/ {

    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;
    private static Parser parser;

    /*private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaBob.jpg"));*/

    public Bob(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BobException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Bob() {
        this("data/tasks.txt");
    }

    public void run() {
        Scanner obj = new Scanner(System.in);

        while (true) {
            String input = obj.nextLine();

            if (parser.isMark(input)) {
                ui.markTask(tasks, parser.getMarkDigit(input));
            } else if (parser.isDelete(input)) {
                ui.deleteTask(tasks, parser.getDeleteDigit(input));
            } else if (parser.isFind(input)) {
                ui.findTask(tasks, parser.findKeyword(input));
            } else if (input.equals("bye")) {
                ui.printGoodbye();
                storage.saveNewList(tasks);
                return;
            } else if (input.equals("list")) {
                ui.printTasks(tasks);
            } else {
                try {
                    ui.checkAndAddTask(tasks, input);
                } catch (BobException e) {
                    System.out.println(e.toString());
                }
            }
        }
    }

    /*@Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
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
        stage.setTitle("Bob");
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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }*/

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    /*private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }*/

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    /*private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }*/

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        //return "Bob heard: " + input;
        if (parser.isMark(input)) {
            return ui.markTask(tasks, parser.getMarkDigit(input));
        } else if (parser.isDelete(input)) {
            return ui.deleteTask(tasks, parser.getDeleteDigit(input));
        } else if (parser.isFind(input)) {
            return ui.findTask(tasks, parser.findKeyword(input));
        } else if (input.equals("bye")) {
            return ui.printGoodbye();
        } else if (input.equals("list")) {
            return ui.printTasks(tasks);
        } else {
            try {
                return ui.checkAndAddTask(tasks, input);
            } catch (BobException e) {
                //System.out.println(e.toString());
                return e.toString();
            }
        }
    }

    public static void main(String[] args) {
        new Bob("data/tasks.txt").run();
    }

}
