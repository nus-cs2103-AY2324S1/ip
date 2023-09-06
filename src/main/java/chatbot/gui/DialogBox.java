package chatbot.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.IOException;

public class DialogBox extends HBox {
    @FXML
    private Text text;
    @FXML
    private ImageView profileImage;

    public DialogBox(String label, Image image) {
        super(5);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.text.setText(label);
        this.profileImage.setImage(image);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tempList = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tempList);
        this.getChildren().setAll(tempList);
    }

    public static DialogBox getUserDialogBox(String label, Image image) {
        return new DialogBox(label, image);
    }

    public static DialogBox getChatBotDialogBox(String label, Image image) {
        DialogBox tempDB = new DialogBox(label, image);
        tempDB.flip();
        tempDB.text.setTextAlignment(TextAlignment.LEFT);
        return tempDB;
    }

}
