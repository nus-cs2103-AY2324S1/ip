package duke.helper;

import duke.MeowBot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


/**
 * Class for DialogBox to represent user interaction with the bot
 */

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor to show the dimensions of the image
     * @param l Label name to be shown
     * @param iv Properties and size of image to be shown
     */

    public DialogBox(Label l, ImageView iv, MeowBot.dialog type) {
        text = l;
        displayPicture = iv;
        text.setWrapText(true);
        text.setPadding(new Insets(5, 5, 5, 5));
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        Circle dpClip = generateCircle(iv);
        displayPicture.setClip(dpClip);
        displayPicture.setX(10);


        Background background = new Background(new BackgroundFill(Color.LIGHTBLUE, null, null));
        if (type == MeowBot.dialog.MEOWBOT) {
            background = new Background(new BackgroundFill(Color.LIGHTPINK, null, null));
        }
        this.setBackground(background);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv, MeowBot.dialog.USER);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv, MeowBot.dialog.MEOWBOT);
        db.flip();
        return db;
    }
    private Circle generateCircle(ImageView iv) {
        double radius = Math.max(iv.getFitWidth() / 2, iv.getFitHeight() / 2);
        double centerX = iv.getFitWidth() / 2 + 10;
        double centerY = iv.getFitHeight() / 2;
        return new Circle(centerX, centerY, radius);
    }

}
