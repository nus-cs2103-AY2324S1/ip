package prts;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import prts.command.Command;
import prts.command.CommandParser;


/**
 * A chatbot and to-do list that can receive text input from the user, parse it, and execute
 * predefined commands.
 */
public class Prts extends Application {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private boolean isFirstMessage = true;

    private String fileName = "prts.PRTS.txt";
    private String[] directories = {"data"};

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image doc = new Image(this.getClass().getResourceAsStream("/images/doc.jpg"));
    private Image prts = new Image(this.getClass().getResourceAsStream("/images/prts.jpg"));

    /**
     * Constructs a PRTS object, and initializes its fields.
     */
    public Prts() {
        ui = new Ui();
        storage = new Storage(fileName, directories);
        try {
            tasks = storage.load();
        } catch (CreateNewSaveException | NewSaveFailedException e) {
            ui.displayMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setTitle("Prts");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing PRTS' reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), doc),
                DialogBox.getPrtsDialog(getResponse(userInput.getText()), prts)
        );
        userInput.clear();
    }


    /**
     * Generates a response to the user input.
     */
    String getResponse(String input) {

        if (isFirstMessage) {
            isFirstMessage = false;
            return ui.showWelcome();
        }

        try {
            Command c = CommandParser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (PrtsException e) {
            return e.getMessage();
        }

    }

}
