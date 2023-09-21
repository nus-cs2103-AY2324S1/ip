package com.cloud.chatbot.ui;

import com.cloud.chatbot.Cloud;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;



/**
 * A custom JavaFX node for displaying chat messages.
 */
public class MessageRow extends HBox {
    public static final double PICTURE_WIDTH = 100;
    public static final double PICTURE_HEIGHT = 100;

    /**
     * Constructs a MessageRow from the user's perspective (profile picture on
     * right).
     *
     * @param text The Label for the message text.
     * @param isUser Whether the message is by the user.
    */
    public MessageRow(Label text, boolean isUser) {
        this.setPadding(new Insets(Ui.PADDING));

        text.setPadding(new Insets(Ui.PADDING));
        text.setWrapText(true);

        ImageView picture = new ImageView(
            new Image(
                Cloud.class.getResourceAsStream(
                    isUser
                        ? "/images/user.png"
                        : "/images/cloud.png"
                )
            )
        );
        picture.setFitWidth(MessageRow.PICTURE_WIDTH);
        picture.setFitHeight(MessageRow.PICTURE_HEIGHT);

        if (isUser) {
            this.setAlignment(Pos.TOP_RIGHT);
            this.getChildren().addAll(text, picture);
        } else {
            this.setAlignment(Pos.TOP_LEFT);
            this.getChildren().addAll(picture, text);
        }
    }
}
