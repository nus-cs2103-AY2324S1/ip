package echobot.utilities;

import java.util.Optional;

import echobot.EchoBot;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 * Shows an alert box for errors outside user mis-input, such as file not found
 */
public class AlertBox {

    private Alert alert = new Alert(Alert.AlertType.ERROR);

    /**
     * Creates a new AlertBox object
     *
     * @param errorType The type of error
     * @param errorMessage The message content of the error
     */
    public AlertBox(String errorType, String errorMessage) {
        alert.setTitle("ERROR");
        alert.setHeaderText(errorType);
        alert.setContentText(errorMessage);
        ButtonType closeButton = new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(closeButton);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == closeButton) {
            EchoBot.stopBot();
        }
    }

    /**
     * Displays the alert box on the user's screen
     */
    public void show() {
        alert.show();
    }
}
