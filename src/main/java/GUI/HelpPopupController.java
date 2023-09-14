package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelpPopupController {
    @FXML
    private Button closeButton;

    @FXML
    private Label helpLabel;

    public void setHelpText(String text) {
        helpLabel.setText(text);
    }
    @FXML
    private void closePopup() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
