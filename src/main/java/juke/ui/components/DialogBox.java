package juke.ui.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

/**
 * Abstract class that is used to represent a dialog box that is
 * displayed to the user through the GUI.
 */
public abstract class DialogBox extends HBox {
    /**
     * Inverts the order of the widgets within this dialog box. This method is left here as a quick way
     * to reverse the order of the child widgets. This method will be deprecated in the future.
     */
    @Deprecated
    public void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> childWidgets = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(childWidgets);
        this.getChildren().setAll(childWidgets);
    }

    /**
     * Creates a dialog box that represents the user's inputs.
     *
     * @param text Text to render in the dialog box
     * @return {@code UserDialog} object
     */
    public static DialogBox ofUser(String text) {
        return new UserDialog(text);
    }

    /**
     * Creates a dialog box that represents Juke's outputs.
     *
     * @param text Text to render in the dialog box
     * @return {@code JukeDialog} object
     */
    public static DialogBox ofJuke(String text) {
        return new JukeDialog(text);
    }
}
