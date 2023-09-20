package chatbot.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class DialogBox extends HBox {
    @FXML
    private Text text;
    @FXML
    private Rectangle box;
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
        this.setBoxDimensions();
        this.profileImage.setImage(image);
    }

    private void setBoxDimensions() {
        this.box.setWidth(this.text.getLayoutBounds().getWidth() + 20);
        this.box.setHeight(this.text.getLayoutBounds().getHeight() + 20);
    }

    private void flip() {
        this.setAlignment(Pos.CENTER_LEFT);
        text.setTextAlignment(TextAlignment.LEFT);
        ObservableList<Node> tempList = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tempList);
        this.getChildren().setAll(tempList);
    }

    private void setBackgroundColor(boolean isErrorMessage) {
        this.box.setFill(Paint.valueOf(isErrorMessage
                                        ? "#ff4040"
                                        : "#ff931f"));
    }

    public static DialogBox getUserDialogBox(String label, Image image) {
        return new DialogBox(label, image);
    }

    public static DialogBox getChatBotDialogBox(String label, Image image, boolean isErrorMessage) {
        DialogBox tempDB = new DialogBox(label, image);
        tempDB.flip();
        tempDB.setBackgroundColor(isErrorMessage);
        return tempDB;
    }

}
