package duke.ui;

import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class GobbleChatContainer extends VBox {
    public GobbleChatContainer() {
        super();
        super.setPrefHeight(Region.USE_COMPUTED_SIZE);
        super.setStyle("-fx-background-color: #FFFFFF;");
    }

    public void addMessage(String message, String from) {
        super.getChildren().add(new GobbleMessage(message, from));
    }
}
