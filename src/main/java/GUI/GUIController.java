package GUI;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class GUIController implements Initializable {

    private Circle headerCircle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        headerCircle.setStroke(Color.color(0,255,0));
        Image im = new Image("Pics/tumblr_67c47d22da73ac2ba89e1e97bce6e525_76dfa232_400.png", false);
        headerCircle.setFill(new ImagePattern(im));

    }
}
