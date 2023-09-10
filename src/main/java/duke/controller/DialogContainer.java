package duke.controller;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Represents the dialog container containing all the messages.
 */
public class DialogContainer extends VBox {

    /**
     * Constructs the dialog container.
     * @param scrollPane The scrollPane that this container is within.
     */
    public DialogContainer(ScrollPane scrollPane) {
        this.setPrefHeight(Region.USE_COMPUTED_SIZE - 60.0);
        this.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

}
