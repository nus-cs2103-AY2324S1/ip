package duke.controller;

import java.util.Objects;

import duke.main.Cleo;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;

/** Create and setup MainWindow with related nodes */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    private Cleo cleo;
    private final Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/user.png")));
    private final Image errorImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/sleeping_cleo.gif")));
    private final Image questionMarkImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/question_mark.png")));
    private final Image cleoImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/dancing_cleo.gif")));
    private final AudioClip plonkSound = new AudioClip(Objects.requireNonNull(getClass()
            .getResource("/audio/click.mp3")).toExternalForm());

    @FXML
    public void initialize() {
        dialogContainer.heightProperty().addListener((observable, oldValue, newValue) -> scrollPane.setVvalue(1.0));
    }

    public void setCleo(Cleo d) {
        cleo = d;
    }

    @FXML
    private void handleUserInput() {
        plonkSound.play();
        String input = userInput.getText();
        String response = cleo.getResponse(input);
        int messageType = cleo.getMessageType();

        Image cleoImage;
        boolean isError = false;
        if (messageType == Cleo.ERROR_MESSAGE_TYPE) {
            cleoImage = errorImage;
            isError = true;
        } else if (messageType == Cleo.NORMAL_MESSAGE_TYPE) {
            cleoImage = this.cleoImage;
        } else {
            cleoImage = questionMarkImage;
            isError = true;
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, cleoImage, isError)
        );
        userInput.clear();
        if (cleo.isExit()) {
            System.exit(0);
        }
    }
}
