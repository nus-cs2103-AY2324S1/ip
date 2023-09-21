package duke.controller;

import duke.main.Cleo;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

import java.util.Objects;


public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    private Cleo cleo;
    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.png")));
    private final Image errorImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/sleeping_cleo.gif")));
    private final Image questionMarkImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/question_mark.png")));
    private final Image cleoImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/dancing_cleo.gif")));

    private final AudioClip plonkSound = new AudioClip(Objects.requireNonNull(getClass().getResource("/audio/click.mp3")).toExternalForm());

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
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
    }
}
