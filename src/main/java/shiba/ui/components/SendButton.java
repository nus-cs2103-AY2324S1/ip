package shiba.ui.components;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Button for sending user input to SHIBA-BOT.
 */
public class SendButton extends Button {
    public static final int SEND_BUTTON_WIDTH = 100;
    private static final String BUTTON_TEXT = "Send";

    /**
     * Constructor for SendButton
     */
    public SendButton(EventHandler<MouseEvent> onMouseClicked) {
        super(BUTTON_TEXT);
        setPrefSize(SEND_BUTTON_WIDTH, CommandInput.TEXT_FIELD_HEIGHT);

        AnchorPane.setBottomAnchor(this, 1.0);
        AnchorPane.setRightAnchor(this, 1.0);

        setOnMouseClicked(onMouseClicked);
    }
}
