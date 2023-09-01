package didier;

import didier.command.Command;
import didier.exception.DidierException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents a Didier bot that is able to interact with a user, keep track of the list of tasks
 * of the user and save the data in local storage.
 */
public class Didier extends Application {

    private Storage storage;
    private TaskList taskList;
    private UI ui;

    /**
     * The constructor for the Didier bot.
     *
     * @param directoryPath The path to the directory where Didier should store the task list.
     * @param fileName The name of the file where Didier should store the task list.
     */
    public Didier(String directoryPath, String fileName) {
        ui = new UI();
        storage = new Storage(directoryPath, fileName);
        taskList = storage.getTasks();
    }

    /**
     * Alternative constructor for Didier bot with default values for directoryPath and filename.
     */
    public Didier() {
        ui = new UI();
        storage = new Storage("data/", "didier.txt");
        taskList = storage.getTasks();
    }

    /**
     * The main entry point for the user interaction with Didier to begin.
     */
    public void run() {
        this.ui.botGreet();
        boolean isExit = false;
        while (!isExit) {
            // Carry out the action determined by the didier.command
            try {
                String commandString = this.ui.readCommand();
                Command command = Parser.parse(commandString);
                command.execute(this.taskList, this.ui, this.storage);
                isExit = command.isExit();
            } catch (DidierException exception) {
                this.ui.botPrintError(exception);
            } finally {
                if (!isExit) {
                    this.ui.botEndCommand();
                }
            }
        }
        this.ui.botGoodBye();
    }

    public static void main(String[] args) {
        Didier didier = new Didier("data/", "didier.txt");
        didier.run();
    }

    @Override
    public void start(Stage primaryStage) {

        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane(scrollPane, sendButton, userInput);

        primaryStage.setTitle("Didier");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToHeight(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        Double offset = 1.0;
        AnchorPane.setTopAnchor(scrollPane, offset);

        AnchorPane.setBottomAnchor(sendButton, offset);
        AnchorPane.setRightAnchor(sendButton, offset);

        AnchorPane.setLeftAnchor(userInput, offset);
        AnchorPane.setBottomAnchor(userInput, offset);

        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
