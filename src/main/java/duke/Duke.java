package duke;

import duke.filemanagement.Storage;
import duke.task.TaskList;
import duke.userio.InvalidUserInputException;
import duke.userio.Parser;
import duke.userio.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Chatbot of the program.
 */
public class Duke extends Application {
    private final TaskList taskList;
    private final Ui ui;
    private final Storage storage;
    private final Parser parser;
    private boolean botInUse = true;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;


    public Duke() {
        this("src/main/data/duke.txt");
    }

    /**
     * Constructor of Duke.
     * @param filepath Task file to be loaded into Duke.
     */
    public Duke(String filepath) {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.parser = new Parser(ui, taskList, botInUse, storage);

        storage.loadFileToTaskManager(taskList);
    }

    @Override
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
     * Returns response based on user's input and also check if input invokes bot to shut down afterwards.
     * @param input Represents user's input.
     * @return A String response for the chatbot to reply to user.
     */
    String getResponse(String input) {
        try {
            String response = parser.listen(input);
            botInUse = parser.updateBotUsage();
            return response;
        } catch (InvalidUserInputException e) {
            return ui.invalidInputRes();
        }
    }

    /**
     * Returns if the bot is in use.
     * @return Boolean to represent if bot is in use.
     */
    public boolean isBotInUse() {
        return botInUse;
    }

}
