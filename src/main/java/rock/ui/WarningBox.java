package rock.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a warning box
 * for the bot to interact with user
 *
 * @author Alvis Ng (supermii2)
 */
public class WarningBox extends HBox {

    private Label text;

    /**
     * Creates a warning box
     * to indicate a client warning
     * @param l Text of warning box
     */
    public WarningBox(Label l) {
        text = l;
        text.setWrapText(true);

        Image i = new Image(this.getClass().getResourceAsStream("/images/Warning.jpg"));
        ImageView warningImageLeft = new ImageView(i);
        ImageView warningImageRight = new ImageView(i);

        warningImageLeft.setFitWidth(50.0);
        warningImageLeft.setFitHeight(50.0);
        warningImageRight.setFitWidth(50.0);
        warningImageRight.setFitHeight(50.0);

        l.setMinWidth(150.0);
        l.setMinHeight(50.0);
        l.setMaxHeight(50.0);

        l.setAlignment(Pos.CENTER);
        this.setAlignment(Pos.TOP_CENTER);
        this.getChildren().addAll(warningImageLeft, text, warningImageRight);
    }

    public static WarningBox getWarning(Label l) {
        return new WarningBox(l);
    }
}
