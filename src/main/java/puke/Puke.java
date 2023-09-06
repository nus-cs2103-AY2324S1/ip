package puke;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
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
import puke.command.Command;
import puke.command.ErrorCommand;
import puke.gui.DialogBox;
import puke.managers.DataHandler;
import puke.managers.Parser;
import puke.managers.PukeException;
import puke.managers.TaskList;
import puke.managers.Ui;

/**
 * A chatbot that uses overly complicated sentences.
 */
public class Puke extends Application {
    /**
     * List of tasks stored by the chatbot
     */
    private TaskList tasks;
    /**
     * The UI of the chatbot that prints all applicable messages.
     */
    private final Ui ui;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Puke.png"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Constructor for the chatbot
     * @throws IOException when an error occurs with the file reader.
     */
    public Puke() throws IOException {
        this.ui = new Ui();
        try {
            tasks = new TaskList(DataHandler.loadDatabase());
        } catch (PukeException e) {
            new File("ListData.txt").createNewFile();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program
     */
    public void run() {
        ui.startup();
        boolean isExit = false;
        while (!isExit) {
            String command = ui.command();
            String input = ui.input();
            ui.line();
            Command next;
            try {
                next = Parser.parse(command, input);
            } catch (PukeException e) {
                next = new ErrorCommand();
            }
            next.execute(tasks, ui);
            isExit = next.isExit();
        }
    }

    /**
     * Dummy method to create GUI
     * @param stage the stage.
     */
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        stage.setTitle("Puke");
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
        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
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
        return "Duke heard: " + input;
    }

    public static void main(String[] args) throws IOException {
        new Puke().run();
    }
}
