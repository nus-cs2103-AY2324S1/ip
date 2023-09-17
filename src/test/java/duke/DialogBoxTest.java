package duke;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import javafx.scene.image.Image;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

class DialogBoxTest {

    private static final Image SAMPLE_IMAGE = new Image("/images/Tony.png");

    @Test
    void dialogBoxCreationWithNonNullValues() {
        DialogBox userDialog = DialogBox.getUserDialog("Test message", SAMPLE_IMAGE);
        assertNotNull(userDialog, "DialogBox should not be null");

        // Extracting children nodes for assertions
        for (Node child : userDialog.getChildren()) {
            if (child instanceof Label) {
                Label lbl = (Label) child;
                assertEquals("Test message", lbl.getText(), "Label text should match");
            } else if (child instanceof ImageView) {
                ImageView imgView = (ImageView) child;
                assertNotNull(imgView.getImage(), "Image should not be null");
            }
        }
    }

    @Test
    void dialogBoxFlipFunctionality() {
        DialogBox dukeDialog = DialogBox.getDukeDialog("Duke message", SAMPLE_IMAGE);
        assertTrue(dukeDialog.getChildren().get(0) instanceof ImageView, "After flipping, ImageView should be first");
        assertTrue(dukeDialog.getChildren().get(1) instanceof Label, "After flipping, Label should be second");
    }
}
