package duke.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class DialogBoxController {

    @FXML
    private Label contentLabel;

    public void setContent(String text, Pos alignment) {
        contentLabel.setText(text);
        contentLabel.setAlignment(alignment);

        // Set the border here
        contentLabel.setBorder(new Border(new BorderStroke(Color.GRAY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));
    }
}
