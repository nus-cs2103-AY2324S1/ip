package juke.ui.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import juke.Juke;
import juke.commons.exceptions.JukeInitialisationException;
import juke.responses.Dialog;

/**
 * Represents a dialog box containing the user's message.
 */
public class UserDialogBox extends DialogBox {
    /** Container for the dialog box contents. */
    @FXML
    private StackPane dialogBubble;

    /** Background of the dialog box contents. */
    @FXML
    private Region backgroundRegion;

    //@@author asdfghjkxd-reused
    // Style of loading the FXML file is reused from https://se-education.org/guides/tutorials/javaFxPart4.html.
    /** Text within the dialog box. */
    @FXML
    private Label textLabel;

    /** Profile image to display in the chat. */
    @FXML
    private ImageView displayImage;

    /**
     * Constructs an instance of {@code UserDialogBox}.
     *
     * @param dialog Text to display in the dialog box
     */
    public UserDialogBox(Dialog dialog) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Juke.class.getResource("/view/UserDialog.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();

            this.textLabel.setText(dialog.getDialogText());
            this.backgroundRegion.maxHeightProperty().bind(this.textLabel.heightProperty());
            this.backgroundRegion.minHeightProperty().bind(this.textLabel.heightProperty());
            this.backgroundRegion.minWidthProperty().bind(this.textLabel.widthProperty());
            this.backgroundRegion.maxWidthProperty().bind(this.textLabel.widthProperty());
        } catch (Exception e) {
            throw new JukeInitialisationException("I cannot initialise! There was an issue loading the necessary "
                                                          + "FXML files to load up the GUI!");
        }
    }
    //@@author
}
