package com.cloud.chatbot.ui;

import java.io.IOException;

import com.cloud.chatbot.Cloud;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;



/**
 * Custom JavaFX node for displaying chat messages. Has its own self-contained
 * FXMLLoader process where this instance is also the controller and root of the
 * view.
 */
public class MessageRow extends HBox {
    @FXML private ImageView picture;
    @FXML private Label text;

    /**
     * Uses FXMLLoader to set up this node.
     *
     * @param textString The message text.
     * @param isUser Whether the message is by the user.
    */
    public MessageRow(String textString, boolean isUser) {
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
        picture.setImage(
            new Image(
                Cloud.class.getResourceAsStream(
                    isUser
                        ? "/images/user.png"
                        : "/images/cloud.png"
                )
            )
        );

        //TODO background colour, flipping
        // if (isUser) {
        //     this.setAlignment(Pos.TOP_RIGHT);
        //     this.getChildren().addAll(textString, picture);
        // } else {
        //     this.setAlignment(Pos.TOP_LEFT);
        //     this.getChildren().addAll(picture, textString);
        // }
    }
}
