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

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        DialogManager dialogManager = new DialogManager(scrollPane, userInput, sendButton, ui, parser, list, storage);
        dialogManager.initializeDialog();
    }
}

