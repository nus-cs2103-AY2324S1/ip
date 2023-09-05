package com.mimi.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * A container that represents messages being created.
 * @author Yuheng
 */
public class DialogBox extends HBox {

    private Label label;
    private ImageView displayPicture;

    private DialogBox(Label label, ImageView displayPicture) {
        this.label = label;
        this.displayPicture = displayPicture;

        Circle clip = new Circle(50, 55, 45);

        this.setSpacing(10);
        this.setPadding(new Insets(0, 0, 15, 0));

        this.label.setWrapText(true);
        this.label.setLineSpacing(5);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        displayPicture.setClip(clip);

        this.setAlignment(Pos.CENTER_RIGHT);
        this.getChildren().addAll(this.label, displayPicture);
    }

    /**
     * Creates an instance of DialogBox representing the user.
     * @param label represents the text to be displayed in the dialog box.
     * @param displayPicture represents the user icon.
     * @return an instance of DialogBox.
     */
    public static DialogBox userDialogBox(Label label, ImageView displayPicture) {
        DialogBox temp = new DialogBox(label, displayPicture);

        BackgroundFill bf = new BackgroundFill(Color.CORNFLOWERBLUE, new CornerRadii(1), null);
        temp.setBackground(new Background(bf));

        temp.label.setFont(Font.font("Verdana", FontWeight.BOLD, 10));

        return temp;
    }

    /**
     * Creates an instance of DialogBox representing the bot.
     * @param label represents the text to be displayed in the dialog box.
     * @param displayPicture represents Mimi's icon.
     * @return an instance of DialogBox.
     */
    public static DialogBox mimiDialogBox(Label label, ImageView displayPicture) {
        DialogBox temp = new DialogBox(label, displayPicture);

        BackgroundFill bf = new BackgroundFill(Color.SPRINGGREEN, new CornerRadii(1), null);
        temp.setBackground(new Background(bf));

        temp.flip();
        return temp;
    }

    private void flip() {
        this.setAlignment(Pos.CENTER_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

}
