package juke.ui.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

//@@author asdfghjkxd-reused
// Class reused from https://se-education.org/guides/tutorials/javaFxPart3.html.
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
}
//@@author
