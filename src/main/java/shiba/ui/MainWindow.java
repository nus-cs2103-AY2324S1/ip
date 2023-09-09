package shiba.ui;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import shiba.Shiba;
import shiba.ui.components.CommandInput;
import shiba.ui.components.DialogBox;
import shiba.ui.components.DialogNode;
import shiba.ui.components.KeyInputHandler;
import shiba.ui.components.SendButton;

/**
 * Main JavaFX window for displaying the application UI.
 */
public class MainWindow extends Application {
    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 800;
    private static final int WINDOW_HEIGHT_CORRECTION = 40;
    private static MainWindow singleton;

    private DialogBox dialogBox;
    private Timer timer;
    private CommandInput userInput;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage primaryStage) {
        AnchorPane root = new AnchorPane();
        primaryStage.setScene(new Scene(root));

        // Dialog box
        dialogBox = new DialogBox();
        root.getChildren().add(dialogBox);

        // Text input field
        userInput = new CommandInput();
        root.getChildren().add(userInput);

        // Send button
        Button sendButton = new SendButton((event -> sendInput()));
        root.getChildren().add(sendButton);

        // Setup key input handler, timer
        primaryStage.getScene().setOnKeyPressed(new KeyInputHandler(this::sendInput));
        timer = new Timer();

        primaryStage.setTitle(Shiba.getInstance().getName());
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(WINDOW_HEIGHT + WINDOW_HEIGHT_CORRECTION);
        primaryStage.setMinWidth(WINDOW_WIDTH);

        primaryStage.show();

        assert singleton == null : "Multiple instances of MainWindow detected!";
        singleton = this;

        Shiba.getInstance().start();
    }

    /**
     * Adds a dialog node to the dialog box with the bot's reply.
     *
     * @param boxTexts Bot text nodes to be displayed in the dialog box
     */
    public void addBotDialogNode(ArrayList<DialogNode.SubNode> boxTexts) {
        dialogBox.addDialogNode(new DialogNode(false, boxTexts));
    }

    /**
     * Closes the UI window after 1.5s.
     */
    public void close() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
            }
        }, 1500);
    }

    public Timer getTimer() {
        return timer;
    }

    /**
     * Cleans up the UI window, stopping any resources that may be preventing the program from terminating.
     */
    public void cleanUp() {
        timer.cancel();
    }

    /**
     * Gets the input in the text field box and sends it to the bot.
     */
    private void sendInput() {
        String input = userInput.getText();
        if (input.isEmpty()) {
            return;
        }
        userInput.clear();

        ArrayList<DialogNode.SubNode> textNodes = new ArrayList<>();
        textNodes.add(new DialogNode.SubNode(1, input));
        dialogBox.addDialogNode(new DialogNode(true, textNodes));

        // Simulate a delay
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Shiba.getInstance().processUserInput(input);
            }
        }, 1500);
    }

    /**
     * Returns the singleton instance of MainWindow.
     *
     * @return The singleton instance of MainWindow.
     */
    public static MainWindow getInstance() {
        return singleton;
    }
}
