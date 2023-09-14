package duke.ui;

import java.util.ArrayList;

import duke.Duke;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.tasks.Task;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;



/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/elonmusk.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the duke variable.
     *
     * @param d Duke
     */
    public void setDuke(Duke d) {
        duke = d;

        this.dialogContainer.getChildren()
                .add(DialogBox.getDukeDialog("Hello! Type something to get started.", crop(dukeImage, true)));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            ArrayList<Task> modifiedTasks = Parser.parseInput(input, duke.getTaskList());
            String response = Ui.getResponseMessage(Parser.getInputCommand(input), modifiedTasks, duke.getTaskList());
            assert !response.isEmpty() : " Response message is blank!";
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog((input), crop(userImage, true)),
                    DialogBox.getDukeDialog((response), crop(dukeImage, true))
            );
            userInput.clear();

            duke.save();
        } catch (DukeException e) {
            String response = e.getMessage();

            assert !response.isEmpty() : " Error message is blank!";
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog((input), crop(userImage, true)),
                    DialogBox.getDukeDialog(response, crop(dukeImage, true))
            );
            userInput.clear();
        }

    }

    /**
     * Crops an image to a circle.
     *
     * @param img      The image to crop
     * @param toCircle Whether we want a circle
     * @return a cropped image
     */
    private Image crop(Image img, boolean toCircle) {
        double d = Math.min(img.getWidth(), img.getHeight());
        double x = (d - img.getWidth()) / 2;
        double y = (d - img.getHeight()) / 2;
        Canvas canvas = new Canvas(d, d);
        GraphicsContext g = canvas.getGraphicsContext2D();
        if (toCircle) {
            g.fillOval(0, 0, d, d);
            g.setGlobalBlendMode(BlendMode.SRC_ATOP);
        }
        g.drawImage(img, x, y);
        return canvas.snapshot(null, null);
    }
}
