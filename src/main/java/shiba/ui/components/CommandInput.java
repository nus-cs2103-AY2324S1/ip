package shiba.ui.components;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

/**
 * Text input field for the user to enter commands.
 */
public class CommandInput extends TextField {
    public static final int TEXT_FIELD_HEIGHT = 50;
    private static final Font INPUT_FONT = new Font("Arial", 18);

    /**
     * Constructor for CommandInput
     */
    public CommandInput() {
        super();

        setPrefHeight(TEXT_FIELD_HEIGHT);
        setFont(INPUT_FONT);
        AnchorPane.setBottomAnchor(this, 1.0);
        AnchorPane.setLeftAnchor(this, 1.0);
        AnchorPane.setRightAnchor(this, SendButton.SEND_BUTTON_WIDTH + 1.0);
    }
}
