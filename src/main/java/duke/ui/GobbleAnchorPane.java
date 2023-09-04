package duke.ui;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class GobbleAnchorPane extends AnchorPane {

    public GobbleAnchorPane() {
        super();
        super.setPrefSize(400.0, 600.0);
        super.setStyle("-fx-background-color: #000000;");
    }

    public void setElements(Node topNode, Node bottomLeftNode, Node bottomRightNode) {
//        super.getChildren().addAll(topNode, bottomLeftNode, bottomRightNode);
        AnchorPane.setTopAnchor(topNode, 1.0);
        AnchorPane.setLeftAnchor(topNode, 1.0);
        AnchorPane.setRightAnchor(topNode, 1.0);
        AnchorPane.setBottomAnchor(bottomLeftNode, 1.0);
        AnchorPane.setLeftAnchor(bottomLeftNode, 1.0);
        AnchorPane.setBottomAnchor(bottomRightNode, 1.0);
        AnchorPane.setRightAnchor(bottomRightNode, 1.0);
    }
}
