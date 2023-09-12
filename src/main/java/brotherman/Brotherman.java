package brotherman;

import brotherman.commands.Command;
import brotherman.exceptions.BrothermanException;
import brotherman.parser.Parser;
import brotherman.storage.Storage;
import brotherman.tasks.TaskList;
import brotherman.ui.Ui;
import javafx.application.Application;
import javafx.application.Platform;
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
 * Represents a Brotherman chatbot
 */
public class Brotherman extends Application {


    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/riversprite.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/ufosprite.jpg"));

    /**
     * Constructor for Brotherman
     * @param filepath File path of the storage
     */
    public Brotherman(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.taskList = storage.readFromFile();
    }

    /**
     * Constructor for Brotherman
     */
    public Brotherman() {
        this.ui = new Ui();
        this.storage = new Storage("./data/brotherman.txt");
        this.taskList = storage.readFromFile();
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        Brotherman brotherman = new Brotherman("./data/brotherman.txt");
        this.storage = brotherman.storage;
        this.taskList = brotherman.taskList;
        this.ui = brotherman.ui;

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setTitle("Brotherman");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        stage.setOnCloseRequest((event) -> {
            closeProgram();
        }
        );

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
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (BrothermanException e) {
                ui.showError(e.getMessage());
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (BrothermanException e) {
                ui.showError(e.getMessage());
            }
        });

        stage.setScene(scene);
        stage.show();

        // more code to be added here later
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() throws BrothermanException {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    private String getResponse(String input) throws BrothermanException {
        try {
            Command command = Parser.parse(input);
            return command.execute(taskList, ui, storage);
        } catch (BrothermanException e) {
            return ui.showError(e.getMessage());
        }
    }

    private void closeProgram() {
        storage.saveToFile(taskList.list());
        Platform.exit();
    }
}
