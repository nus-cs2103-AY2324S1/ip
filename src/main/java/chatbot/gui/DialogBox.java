package chatbot.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.Node;

public class DialogBox extends HBox {
    public static final double IMAGE_HEIGHT = 50.0;
    public static final double IMAGE_WIDTH = 50.0;
    private final Insets IMAGE_PADDING = new Insets(5.0, 0, 5.0, 0);
    private final Insets Text_Padding = new Insets(5.0, 0, 5.0, 0);
    private Label text;
    private ImageView profileImage;
    private Pane imageContainer;

    public DialogBox(Label label, ImageView image) {
        super(5);

        text = label;
        profileImage = image;

        text.setWrapText(true);
        text.setMaxWidth(Gui.SCROLLPANE_WIDTH * 0.65);
        text.setPadding(Text_Padding);
        profileImage.setFitHeight(IMAGE_HEIGHT);
        profileImage.setFitWidth(IMAGE_WIDTH);

        imageContainer = new Pane();
        imageContainer.setPadding(IMAGE_PADDING);
        imageContainer.getChildren().addAll(profileImage);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(this.text, this.imageContainer);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tempList = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tempList);
        this.getChildren().setAll(tempList);
    }

    public static DialogBox getUserDialogBox(Label label, ImageView image) {
        return new DialogBox(label, image);
    }

    public static DialogBox getChatBotDialogBox(Label label, ImageView image) {
        DialogBox tempDB = new DialogBox(label, image);
        tempDB.flip();
        return tempDB;
    }


}
