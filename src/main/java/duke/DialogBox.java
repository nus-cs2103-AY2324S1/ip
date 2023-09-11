package duke;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;


public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        dialog.setPrefHeight(Region.USE_COMPUTED_SIZE);

        dialog.setPrefWidth(Region.USE_COMPUTED_SIZE);

        // Ensure the dialog box resizes its height based on content
        dialog.setWrapText(true);
        dialog.heightProperty().addListener((observable, oldValue, newValue) -> {
            setPrefHeight(newValue.doubleValue() + displayPicture.getFitHeight());
        });
    }


    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
