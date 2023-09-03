package shiba.ui.components;

import javafx.application.Platform;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import shiba.ui.MainWindow;

import java.util.TimerTask;

/**
 * Dialog box for displaying the conversation between the user and SHIBA-BOT.
 */
public class DialogBox extends ScrollPane {
    private final VBox dialogPane = new VBox();

    /**
     * Constructor for DialogBox
     */
    public DialogBox() {
        super();

        setPrefSize(MainWindow.WINDOW_WIDTH, MainWindow.WINDOW_HEIGHT - CommandInput.TEXT_FIELD_HEIGHT);
        setFitToWidth(true);
        setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        dialogPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
        setContent(dialogPane);
    }

    /**
     * Adds a dialog node to the dialog box.
     *
     * @param dialogNode Dialog node to be added
     */
    public void addDialogNode(DialogNode dialogNode) {
        dialogPane.getChildren().add(dialogNode);
        MainWindow.SINGLETON.getTimer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> setVvalue(1.0));
            }
        }, 100);
    }
}
