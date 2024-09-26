package components;

import tasks.TaskList;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GuiManager {
    private final Stage stage;
    private final Ui ui;
    private final Parser parser;
    private final TaskList list;
    private final Storage storage;

    public GuiManager(Stage stage, Ui ui, Parser parser, TaskList list, Storage storage) {
        this.stage = stage;
        this.ui = ui;
        this.parser = parser;
        this.list = list;
        this.storage = storage;
    }

    public void initializeGui() {
        ScrollPane scrollPane = new ScrollPane();
        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        sendButton.setPrefWidth(60);  // Set the width to 100

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        // Set minimum size for the main layout
        mainLayout.setMinWidth(380);
        mainLayout.setMinHeight(600);

        // Set anchor points for the ScrollPane
        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(scrollPane, 40.0);  // reserve 40 units at the bottom for the TextField and Button
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);

        // Set anchor points for the TextField
        AnchorPane.setBottomAnchor(userInput, 0.0);
        AnchorPane.setLeftAnchor(userInput, 0.0);
        AnchorPane.setRightAnchor(userInput, 60.0);  // reserve 60 units at the right for the Button

        // Set anchor points for the Button
        AnchorPane.setBottomAnchor(sendButton, 0.0);
        AnchorPane.setRightAnchor(sendButton, 0.0);

        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        DialogManager dialogManager = new DialogManager(scrollPane, userInput, sendButton, ui, parser, list, storage);
        dialogManager.initializeDialog();
    }
}

