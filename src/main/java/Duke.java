import java.util.Scanner;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.parser.CommandParser;
import duke.records.ChatRecord;
import duke.ui.ChatView;
import duke.ui.DialogBox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Toh Li Yuan (A0255811H)
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField inputField;
    private Button sendButton;
    private Scene scene;


    private Image user = new Image(this.getClass().getResourceAsStream("/image/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/image/DaDuke.png"));

    public static void main(String[] args) {
        ChatView chatView = new ChatView();
        Scanner sc = new Scanner(System.in);
        CommandParser cp = new CommandParser();
        ChatRecord chatRecord = new ChatRecord();
        chatView.displayBasic(chatRecord.loadData());
        chatView.startMessage();
        Command cmd;
        do {
            String command = sc.nextLine();
            cmd = cp.parseCommand(command);
            cmd.init(chatRecord);
            String out = cmd.execute();
            chatView.displayOutput(out);
        } while (!ByeCommand.isBye(cmd));
    }

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        inputField = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, inputField, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 555);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        inputField.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(inputField, 1.0);
        AnchorPane.setBottomAnchor(inputField, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        inputField.setOnAction((event) -> {
           handleUserInput();
        });
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        Label userText = new Label(inputField.getText());
        Label dukeText = new Label(getResponse(inputField.getText()));
        dialogContainer.getChildren().addAll(
                new DialogBox(userText, new ImageView(user)),
                new DialogBox(dukeText, new ImageView(duke))
        );
        inputField.clear();
    }

    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
