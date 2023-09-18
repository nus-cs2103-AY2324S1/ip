package main;

import command.CommandException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Scanner;

public class Ui extends Application {
    private Stage window;
    private static Ui INSTANCE;
    private VBox dialogContainer;

    private Image portraitUser;
    private Image portraitBot;

    private int portraitSize = 50;


    /**
     * Start method for JavaFX
     *
     * @param primaryStage The primary stage provided by JavaFX
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        INSTANCE = this;
        this.window = primaryStage;
        AnchorPane anchorPane = new AnchorPane();
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        this.dialogContainer = dialogContainer;
        scrollPane.setContent(dialogContainer);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        TextField messageInputArea = new TextField();
        messageInputArea.setPrefSize(350, 20);
        messageInputArea.setLayoutX(0);
        messageInputArea.setLayoutY(600);
        Button sendMessageButton = new Button("Send");
        sendMessageButton.setPrefSize(50, 20);
        sendMessageButton.setLayoutX(350);
        sendMessageButton.setLayoutY(600);
        scrollPane.setPrefSize(400, 590);
        anchorPane.getChildren().addAll(scrollPane, messageInputArea, sendMessageButton);
        Scene mainScene = new Scene(anchorPane);
        primaryStage.setTitle("Chat with " + Main.getInstance().getName());
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(400);
        anchorPane.setPrefSize(400, 600);
        primaryStage.setScene(mainScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            ButtonType exitBtn = new ButtonType("Exit");
            ButtonType cancelBtn = new ButtonType("Cancel");
            Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION,"", cancelBtn, exitBtn);
            closeConfirmation.setHeaderText("Do you really want to exit?");
            closeConfirmation.showAndWait().ifPresent(
                    result->{
                        if(result == exitBtn) {
                            this.byeAndExit();
                        }
                    }
            );
            event.consume();
        });

        sendMessageButton.setOnMouseClicked(event -> {
            String message = messageInputArea.getText();
            messageInputArea.setText("");
            this.addMessageBubble(message, true, false);
            Main.getInstance().getParser().executeCommand(message);
        });

        messageInputArea.setOnAction(event -> {
            String message = messageInputArea.getText();
            messageInputArea.setText("");
            this.addMessageBubble(message, true, false);
            Main.getInstance().getParser().executeCommand(message);
        });

        this.portraitUser = new Image(this.getClass().getResourceAsStream("/images/Portrait_User.png"));
        this.portraitBot = new Image(this.getClass().getResourceAsStream("/images/Portrait_Bot.png"));
        Main.getInstance().getParser().executeCommand("intro");
    }


    /**
     * Hide the main window, pops a message box to say goodbye to the user and finally exit the whole program.
     */
    public void byeAndExit() {
        assert(Main.getInstance() != null);
        this.window.hide();
        ButtonType okBtn = new ButtonType("Bye");
        Alert closeConfirmation = new Alert(Alert.AlertType.NONE,"", okBtn);
        closeConfirmation.setHeaderText("Bye, hope to see you soon!");
        closeConfirmation.showAndWait();
        Main.getInstance().exit();
    }


    /**
     * Outputs message to the user
     *
     * @param content the message content
     */
    public void say(String content) {
        this.addMessageBubble(content, false, false);
    }

    /**
     * Outputs error message to the user
     *
     * @param content the error message content
     */
    public void sayError(String content) {
        this.addMessageBubble(content, false, true);
    }

    private void addMessageBubble(String content, boolean isUser, boolean isError) {
        Pane messageBubble = new Pane();
        messageBubble.setBackground(new Background(new BackgroundFill(isUser ? Color.LIGHTGREEN : Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        ImageView portrait = new ImageView(isUser ? this.portraitUser : this.portraitBot);
        portrait.setFitWidth(this.portraitSize);
        portrait.setFitHeight(this.portraitSize);
        portrait.setLayoutX(isUser ? 400 - this.portraitSize : 0);
        Rectangle portraitRect = new Rectangle(this.portraitSize, this.portraitSize);
        portraitRect.setArcWidth(this.portraitSize);
        portraitRect.setArcHeight(this.portraitSize);
        portrait.setClip(portraitRect);
        Label label = new Label(content);
        label.setPrefWidth(400 - this.portraitSize - 20);
        label.setMinHeight(this.portraitSize);
        label.setLayoutX(isUser ? 10 : this.portraitSize + 10);
        label.setWrapText(true);
        label.setTextFill(isError ? Color.RED : Color.BLACK);
        label.setTextAlignment(isUser ? TextAlignment.RIGHT : TextAlignment.LEFT);
        label.setAlignment(isUser ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        messageBubble.getChildren().addAll(label, portrait);
        messageBubble.prefHeightProperty().bind(label.heightProperty());
        this.dialogContainer.getChildren().add(messageBubble);
    }


    /**
     * Returns the Ui singleton instance
     *
     * @return the instance
     */
    public static Ui getInstance() {
        return INSTANCE;
    }
}
