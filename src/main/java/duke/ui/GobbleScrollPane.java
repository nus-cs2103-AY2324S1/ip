package duke.ui;

import javafx.scene.control.ScrollPane;

public class GobbleScrollPane extends ScrollPane {

    public GobbleScrollPane() {
        super();
        super.setPrefSize(385, 535);
        super.setHbarPolicy(ScrollBarPolicy.NEVER);
        super.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        super.setVvalue(1.0);
        super.setFitToWidth(true);
    }
}
