package shiba.ui.components;

import java.util.TimerTask;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import shiba.ui.MainWindow;

/**
 * Dialog box for displaying the conversation between the user and SHIBA-BOT.
 */
public class DialogBox extends ScrollPane {
    private static final double DIALOG_NODE_SPACING = 10.0;
    private final VBox dialogPane = new VBox(DIALOG_NODE_SPACING);

    /**
     * Constructor for DialogBox
     */
    public DialogBox() {
        super();

        setFitToWidth(true);
        setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        AnchorPane.setLeftAnchor(this, 10.0);
        AnchorPane.setRightAnchor(this, 1.0);
        AnchorPane.setTopAnchor(this, 10.0);
        AnchorPane.setBottomAnchor(this, CommandInput.TEXT_FIELD_HEIGHT + 1.0);
        setStyle("-fx-background-color:transparent;");

        dialogPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogPane.setPadding(new Insets(0, 10, 0, 0));
        dialogPane.setFillWidth(true);
        setContent(dialogPane);
    }

    /**
     * Adds a dialog node to the dialog box.
     *
     * @param dialogNode Dialog node to be added
     */
    public void addDialogNode(DialogNode dialogNode) {
        dialogPane.getChildren().add(dialogNode);
        MainWindow mainWindow = MainWindow.getInstance();
        if (mainWindow != null) {
            mainWindow.getTimer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> setVvalue(1.0));
                }
            }, 100);
        }
    }
}
