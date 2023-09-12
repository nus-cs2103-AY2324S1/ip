package duke.ui;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The main display window for duke GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextField inputField;
    @FXML
    private VBox dialogContainer;
    @FXML
    private Button sendButton;

    private Duke duke;
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/image/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/image/DaDuke.png"));

    /**
     * Initialises the duke GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        anchorPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            dialogContainer.setPrefWidth(newValue.doubleValue() - 12);
        });
    }

    public void setDuke(Duke d) {
        this.duke = d;
    }

    @FXML
    private void handleUserInput() {
        String input = inputField.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        inputField.clear();
        if (duke.isTerminate()) {
            exitApp();
        }
    }

    private String getResponse(String input) {
        return duke.getResponse(input);
    }

    private void exitApp() {
        Executor delayed = CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS);
        CompletableFuture.supplyAsync(() -> "test", delayed).thenRun(() -> {
            Platform.exit();
            System.exit(0);
        });
    }

}
