package duke;
import duke.exceptions.DukeException;
import duke.gui.DialogBox;

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

/**
 * duke.Main class for the Duke application.
 * This class handles user interactions and manages tasks using the Archive class.
 */
public class Duke extends Application{
    private Storage storage;
    private TaskList tasks;
    private UI ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Head.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Rickmorty.png"));

    /**
     * Constructs a Duke instance with the specified file path.
     */

    public Duke() {
        ui = new UI();
        storage = new Storage("data/saved.txt");
        tasks = new TaskList(storage.load());
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

        stage.setTitle("Duke");
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
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
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
        String input = userInput.getText();
        String dukeProcessedText = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), userImage),
                DialogBox.getDukeDialog(dukeProcessedText, dukeImage)
        );
        userInput.clear();
    }

    public String getResponse(String input) {
        String dukeProcessedText;
        String[] parsedText = Parser.parse(input, tasks);
        switch(parsedText[0]) {
            case "mark" :
                dukeProcessedText = tasks.markTask(Integer.parseInt(parsedText[1]));
                break;
            case "unmark" :
                dukeProcessedText = tasks.unmarkTask(Integer.parseInt(parsedText[1]));
                break;
            case "delete" :
                dukeProcessedText = tasks.deleteTask(Integer.parseInt(parsedText[1]));
                break;
            case "find" :
                try {
                    dukeProcessedText = tasks.find(parsedText[1]);
                } catch (DukeException e) {
                    dukeProcessedText = e.getMessage();
                }
                break;
            case "list" :
                dukeProcessedText = tasks.getAll();
                break;
            case "exception" :
                dukeProcessedText = parsedText[1];
                break;
            case "todo" :
            case "deadline" :
            case "event" :
                try {
                    dukeProcessedText = tasks.addTask(parsedText);
                } catch (DukeException e) {
                    dukeProcessedText = e.getMessage();
                }
                break;
            default:
                dukeProcessedText = "Invalid input";
                break;
        }
        storage.save(tasks);
        return dukeProcessedText;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */

    /**
     * Runs the Duke application, handling user interactions and task management.
     */
    public void run() {
        ui.printIntro();
        while (true) {
            String input = ui.getInput();
        }
    }

    /**
     * The entry point for running the Duke application.
     *
     * @param args The command-line arguments.
     */
//    public static void main(String[] args) {
//        new Duke().run();
//    }
}
