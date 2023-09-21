package com.cloud.chatbot.ui;

import java.io.IOException;

import com.cloud.chatbot.Cloud;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;



/**
 * Custom JavaFX Node for displaying chat messages. Has its own self-contained
 * FXMLLoader process where this instance is also the controller and root of the
 * view.
 */
public class MessageRow extends HBox {
    /**
     * Whether the message is formatted for the user, or the bot.
     */
    private boolean isUser;

    @FXML private ImageView picture;
    @FXML private Label text;

    /**
     * Uses FXMLLoader to set up this Node.
     *
     * @param textString The message text.
     * @param _isUser Whether message is by user.
    */
    public MessageRow(String textString, boolean _isUser) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/MessageRow.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // After load(), @FXML fields are populated
        text.setText(textString);

        this.setIsUser(_isUser);
    }

    private void setIsUser(boolean _isUser) {
        this.isUser = _isUser;

        if (this.isUser) {
            this.setAlignment(Pos.TOP_RIGHT);
            this.getChildren().setAll(this.text, this.picture);

            picture.setImage(
                new Image(
                    Cloud.class.getResourceAsStream("/images/user.png")
                )
            );
            this.setStyle("-fx-background-color: #DDFFDD");
        } else {
            this.setAlignment(Pos.TOP_LEFT);
            this.getChildren().setAll(this.picture, this.text);

            picture.setImage(
                new Image(
                    Cloud.class.getResourceAsStream("/images/cloud.png")
                )
            );
            this.setStyle("-fx-background-color: #DDFFFF");
        }
    }
}
