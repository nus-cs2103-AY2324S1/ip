package shiba.ui;

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
        CommandInput userInput = new CommandInput();
        root.getChildren().add(userInput);

        timer = new Timer();

        // Send button
        Button sendButton = new SendButton((event -> {
            String input = userInput.getText();
            if (input.isEmpty()) {
                return;
            }
            userInput.clear();
            dialogBox.addDialogNode(new DialogNode(input, true));

            // Simulate a delay
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Shiba.getInstance().processUserInput(input);
                }
            }, 1500);
        }));
        root.getChildren().add(sendButton);

        primaryStage.setTitle(Shiba.getInstance().getName());
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(WINDOW_HEIGHT + WINDOW_HEIGHT_CORRECTION);
        primaryStage.setMinWidth(WINDOW_WIDTH);

        primaryStage.show();

        if (singleton == null) {
            singleton = this;
        } else {
            System.out.println("Warning: Multiple instances of MainWindow detected!");
        }
    }

    /**
     * Adds a dialog node to the dialog box with the bot's reply.
     *
     * @param boxText Bot text to be displayed in the dialog box
     */
    public void addBotDialogNode(String boxText) {
        dialogBox.addDialogNode(new DialogNode(boxText, false));
    }

    /**
     * Closes the UI window after 1.5s.
     */
    public void close() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                timer.cancel();
            }
        }, 1500);
    }

    public Timer getTimer() {
        return timer;
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
