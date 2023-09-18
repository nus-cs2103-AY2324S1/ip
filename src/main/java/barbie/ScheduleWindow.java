package barbie;


import barbie.types.Task;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Controller for ScheduleWindow. Provides the layout for the other controls.
 */
public class ScheduleWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox scheduleContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private final Barbie barbie = new Barbie();

    /**
     * Initialises the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(scheduleContainer.heightProperty());
    }




}
