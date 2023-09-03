package duke;

//import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;

public class MainWindow extends AnchorPane {
    @FXML
    private ImageView sendButton;
    @FXML
    private TextArea textField;
    @FXML
    private ScrollPane dialogContainer;
    @FXML
    private VBox dialogBox;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/tony.png"));
    private Image jarvis = new Image(this.getClass().getResourceAsStream("/images/duke.png"));
    private Duke duke;

    @FXML
    public void initialize() {
        dialogContainer.setVvalue(1.0);
        dialogBox.heightProperty().addListener((observable) -> dialogContainer.setVvalue(1.0));
        dialogContainer.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        dialogContainer.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        dialogBox.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogBox.getChildren().add(DialogBox.getDukeDialog("Hi!", jarvis));
    }

    @FXML
    void onClick() {
        Stage mainWindow = (Stage) textField.getScene().getWindow();
        String input = textField.getText().replace("\n", "");;
        String response = this.duke.getResponse(input);
        textField.clear();
        dialogBox.getChildren().addAll(
                DialogBox.getUserDialog(input, user),
                DialogBox.getDukeDialog(response, jarvis)
        );
//        if (input.equals("bye")) {
//            Platform.exit();
//        }
    }

    @FXML
    void onEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            onClick();
        }
    }

    public void setDuke(Duke d) {
        duke = d;
    }

}
