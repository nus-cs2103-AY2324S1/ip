package duke;
import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label.
 * Containing text from the speaker.
 */
public class DialogBox extends HBox {
	@FXML
	private Label dialog;
	@FXML
	private ImageView displayPicture;

	/**
	 * Represents a DialogBox object which users interact with.
	 *
	 * @param text Text in the dialog box.
	 * @param img Image to be loaded.
	 */
	private DialogBox(String text, Image img) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(duke.MainWindow.class.getResource("/view/DialogBox.fxml"));
			fxmlLoader.setController(this);
			fxmlLoader.setRoot(this);
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		dialog.setText(text);
		displayPicture.setImage(img);
	}

	/**
	 * Flips the dialog box such that the ImageView is on the left and text on the right.
	 */
	private void flip() {
		ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
		Collections.reverse(tmp);
		getChildren().setAll(tmp);
		setAlignment(Pos.TOP_LEFT);
	}

	/**
	 * Returns a Dialog Box.
	 *
	 * @param text the text entered.
	 * @param img the image.
	 * @return a Dialog Box.
	 */
	public static DialogBox getUserDialog(String text, Image img) {
		return new DialogBox(text, img);
	}

	/**
	 * Returns DialogBox.
	 *
	 * @param text the text.
	 * @param img the image.
	 * @return DialogBox.
	 */

	public static DialogBox getDukeDialog(String text, Image img) {
		var db = new DialogBox(text, img);
		db.flip();
		return db;
	}
}
