package duke.ui;

import javafx.scene.control.Button;

public class GobbleButton extends Button {

    public GobbleButton() {
        super();
        super.setPrefSize(60, 60);
        super.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000; -fx-font-size: 15px;");
    }

    public GobbleButton(String label) {
        super();
        super.setText(label);
        this.setLayout();

    }

    private void setLayout() {
        this.setPrefSize(60, 60);
        this.setStyle("-fx-cursor: hand; -fx-background-color: #FFFFFF; -fx-text-fill: #000000; -fx-font-size: 15px;");
        // set pointer cursor
        
        this.onHover();
    }

    public void onHover() {
        this.setOnMouseEntered((event) -> {
            this.setStyle("-fx-cursor: hand; -fx-background-color: #000000; -fx-text-fill: #FFFFFF; -fx-font-size: 15px;");
        });

        this.setOnMouseExited((event) -> {
            this.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000; -fx-font-size: 15px;");
        });
    }
}
