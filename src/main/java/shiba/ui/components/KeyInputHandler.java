package shiba.ui.components;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Handles key input from the user.
 */
public class KeyInputHandler implements EventHandler<KeyEvent> {
    private final Runnable onEnter;

    /**
     * Constructor for KeyInputHandler.
     *
     * @param onEnter The action to be performed when the user presses the enter key.
     */
    public KeyInputHandler(Runnable onEnter) {
        this.onEnter = onEnter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(KeyEvent event) {
        if (KeyEvent.KEY_PRESSED.equals(event.getEventType())) {
            if (event.getCode() == KeyCode.ENTER) {
                onEnter.run();
            }
        }
    }
}
