package chatbot;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;

public class Gui {
    public static final double ANCHORPANE_WIDTH = 400.0;
    public static final double ANCHORPANE_HEIGHT = 600.0;
    public static final double SCROLLPANE_WIDTH = 385.0;
    public static final double SCROLLPANE_HEIGHT = 535.0;
    public static final double USERINPUT_WIDTH = 325.0;
    public static final double SENDBUTTON_WIDTH = 55.0;
    private ScrollPane chatHistoryScrollPane;
    private VBox chatContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private AnchorPane mainLayout;

    public Gui() {
        this.chatHistoryScrollPane = new ScrollPane();
        this.chatContainer = new VBox();
        this.chatHistoryScrollPane.setContent(chatContainer);
        this.userInput = new TextField();
        this.sendButton = new Button("Send");

        this.mainLayout = new AnchorPane();
        this.mainLayout.getChildren().addAll(this.chatHistoryScrollPane, this.userInput, this.sendButton);

        this.scene = new Scene(this.mainLayout);

        initStyle();

        initActions();
    }

    private void initStyle() {
        this.mainLayout.setPrefSize(ANCHORPANE_WIDTH, ANCHORPANE_HEIGHT);

        this.chatHistoryScrollPane.setPrefSize(SCROLLPANE_WIDTH, SCROLLPANE_HEIGHT);
        this.chatHistoryScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.chatHistoryScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.chatHistoryScrollPane.setVvalue(1.0);
        this.chatHistoryScrollPane.setFitToWidth(true);

        this.chatContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        this.chatContainer.heightProperty().addListener((observable) -> {
          this.chatHistoryScrollPane.setVvalue(1.0);
        });

        this.userInput.setPrefWidth(USERINPUT_WIDTH);

        this.sendButton.setPrefWidth(SENDBUTTON_WIDTH);

        AnchorPane.setTopAnchor(this.chatHistoryScrollPane, 1.0);

        AnchorPane.setBottomAnchor(this.userInput, 1.0);
        AnchorPane.setLeftAnchor(this.userInput, 1.0);

        AnchorPane.setBottomAnchor(this.sendButton, 1.0);
        AnchorPane.setRightAnchor(this.sendButton, 1.0);
    }

    private Label getChatLabel(String text) {
        Label chatLabel = new Label(text);
        chatLabel.setWrapText(true);

        return chatLabel;
    }

    private void initActions() {
        this.sendButton.setOnMouseClicked(event -> {
            this.chatContainer.getChildren().add(getChatLabel(this.userInput.getText()));
            this.userInput.clear();
        });

        this.userInput.setOnAction(event -> {
            this.chatContainer.getChildren().add(getChatLabel(this.userInput.getText()));
            this.userInput.clear();
        });
    }

    public Scene getScene() {
        return this.scene;
    }
}
