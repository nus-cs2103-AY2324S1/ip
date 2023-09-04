package duke.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class GobbleMessage extends HBox {
    private final Label text;
    private final Label from;

    public GobbleMessage(String text, String from) {
        super();
        this.text = new Label(text);
        this.from = new Label(from);
        this.text.setWrapText(true);
        this.text.setPrefWidth(300);
        this.text.setStyle("-fx-background-color: #EADDCA; -fx-text-fill: #000000; -fx-font-size: 15px;");
        this.from.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000; -fx-font-size: 15px;");
        this.from.setPrefWidth(60);
        this.getChildren().addAll(this.from, this.text);
    }
}
