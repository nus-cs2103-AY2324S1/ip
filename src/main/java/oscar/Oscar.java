package oscar;

import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.image.ImageView;

import java.util.Objects;
import java.util.Scanner;

import oscar.command.Command;
import oscar.essential.Parser;
import oscar.essential.Storage;
import oscar.essential.TaskList;
import oscar.ui.Ui;
import oscar.exception.OscarException;

/**
 * Chatbot named Oscar that can respond to user input.
 */
public class Oscar extends Application {
    static final String FILE_PATH = "./data/tasklist";

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;

    private final Image user =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.png")));
    private final Image oscar =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/oscar.png")));


    /**
     * Instantiates Oscar with saved data.
     *
     * @param filePath Location of saved task list.
     */
    public Oscar(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (OscarException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param stage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     */
    @Override
    public void start(Stage stage) {
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

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

        // Functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label oscarText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getOscarDialog(oscarText, new ImageView(oscar))
        );
        userInput.clear();
    }

    /**
     *
     * @param input
     * @return
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                Platform.exit();
            };
            return c.execute(tasks, storage);
        } catch (OscarException e) {
            return ui.showError(e);
        }
    }
}
