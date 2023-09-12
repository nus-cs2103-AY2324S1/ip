package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Scanner;

public class Ui extends Application {
    private Stage window;
    private static Ui INSTANCE;
    private VBox dialogContainer;


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
                        if(result == exitBtn){
                            this.byeAndExit();
                        }
                    }
            );
            event.consume();
        });

        sendMessageButton.setOnMouseClicked(event -> {
            String message = messageInputArea.getText();
            messageInputArea.setText("");
            this.addMessageBubble(message, true);
            Main.getInstance().getParser().executeCommand(message);
        });

        messageInputArea.setOnAction(event -> {
            String message = messageInputArea.getText();
            messageInputArea.setText("");
            this.addMessageBubble(message, true);
            Main.getInstance().getParser().executeCommand(message);
        });

        Main.getInstance().getParser().executeCommand("intro");
    }


    /**
     * Hide the main window, pops a message box to say goodbye to the user and finally exit the whole program.
     */
    public void byeAndExit(){
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
        this.addMessageBubble(content, false);
    }

    /**
     * Outputs error message to the user
     *
     * @param content the error message content
     */
    public void sayError(String content) {
        this.addMessageBubble(content, false);
    }

    private void addMessageBubble(String content, boolean isUser) {
        Pane messageBubble = new Pane();
        messageBubble.setBackground(new Background(new BackgroundFill(isUser ? Color.LIGHTGREEN : Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        Label text = new Label(content);
        text.setMaxWidth(400);
        text.setWrapText(true);
        //text.setTextAlignment(isUser ? TextAlignment.RIGHT : TextAlignment.LEFT);
        messageBubble.getChildren().add(text);
        messageBubble.prefHeightProperty().bind(text.heightProperty());
        messageBubble.prefWidthProperty().bind(text.widthProperty());
        this.dialogContainer.getChildren().add(messageBubble);
        //VBox.setMargin(messageBubble, new Insets(0, 0, 0, isUser ? 350 : 0));
    }


    /**
     * Returns the Ui singleton instance
     *
     * @return the instance
     */
    public static Ui getInstance(){
        return INSTANCE;
    }
}
