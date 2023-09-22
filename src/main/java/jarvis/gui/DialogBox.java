package jarvis.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * The Dialog class control represents a dialog box consisting of an ImageView
 * to represent the speaker's face and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final int MESSAGE_BOX_WIDTH = 350;
    private static final int ARC_VALUE = 40;
    private static final int MAX_TEXT_LEN = 50;
    private static final int PFP_SIZE = 30;

    /**
     * Creates a new instance of DialogBox with the specified text and image.
     *
     * @param message The text content of the dialog box.
     * @param img     The image to be displayed in the dialog box.
     */
    private DialogBox(String message, Image img, boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/views/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isUser) {
            buildDialogBox(message, img, true);
        } else {
            buildDialogBox(message, img, false);
        }

    }

    /**
     * Creates a user dialog box.
     *
     * @param text The text content of the user dialog box.
     * @param img  The image to be displayed for the user dialog box.
     * @return A DialogBox representing the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, true);
    }

    /**
     * Creates a Jarvis dialog box.
     *
     * @param text The text content of the Jarvis dialog box.
     * @param img  The image to be displayed for the Jarvis dialog box.
     * @return A DialogBox representing Jarvis's dialog.
     */
    public static DialogBox getJarvisDialog(String text, Image img) {
        var db = new DialogBox(text, img, false);
        db.flip();
        return db;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    @FXML
    private void buildDialogBox(String message, Image img, boolean isUser) {
        Text formattedText = createText(message);
        formattedText.getStyleClass().add("dialog-text");
        Circle circleImage = getCircularImg(img);
        if (isUser) {
            formattedText.setFill(Color.WHITE);
            StackPane dialogBox = createDialogBox(formattedText, Color.rgb(2, 125, 254));
            this.getChildren().addAll(dialogBox, circleImage);
        } else {
            StackPane dialogBox = createDialogBox(formattedText, Color.rgb(233, 233, 233));
            this.getChildren().addAll(dialogBox, circleImage);
        }
    }

    private static Circle getCircularImg(Image img) {
        Circle circleImage = new Circle(0, 0, PFP_SIZE);
        circleImage.setFill(new ImagePattern(img));
        return circleImage;
    }

    private StackPane createDialogBox(Text text, Color fillColor) {
        Rectangle messageBox = createMessageBox(text, fillColor);
        StackPane stackPane = new StackPane(messageBox, text);
        styleDialogBox(stackPane);
        return stackPane;
    }

    private static void styleDialogBox(StackPane stackPane) {
        StackPane.setMargin(stackPane, new Insets(10, 0, 10, 0)); // Margin at top and bottom
        stackPane.setPadding(new Insets(10));
    }

    private Rectangle createMessageBox(Text formattedText, Color fillColor) {
        assert formattedText != null : "Text cannot be null.";
        Rectangle messageBox = createCustomMessageBox(formattedText, fillColor);
        styleMessageBox(messageBox, fillColor);
        return messageBox;
    }

    private static Rectangle createCustomMessageBox(Text formattedText, Color fillColor) {
        double messageBoxWidth = formattedText.getBoundsInLocal().getWidth() + 40;
        double messageBoxHeight = formattedText.getBoundsInLocal().getHeight() + 20;
        Rectangle styledMessageBox = new Rectangle(messageBoxWidth, messageBoxHeight);
        styleMessageBox(styledMessageBox, fillColor);
        return styledMessageBox;
    }

    private static void styleMessageBox(Rectangle messageBox, Color fillColor) {
        messageBox.setFill(fillColor);
        messageBox.setArcWidth(ARC_VALUE);
        messageBox.setArcHeight(ARC_VALUE);
    }

    private Text createText(String string) {
        assert string != null : "String to be converted cannot be null";
        return wrapText(new Text(string));
    }

    private Text wrapText(Text text) {
        String originalText = text.getText();
        String[] lines = originalText.split("\n");

        StringBuilder wrappedText = new StringBuilder();

        for (String line : lines) {
            wrapLine(line, wrappedText);
        }

        return new Text(wrappedText.toString());
    }

    private static void wrapLine(String line, StringBuilder wrappedText) {
        String[] words = line.split("\\s+");
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            if (word.length() > MAX_TEXT_LEN) {
                wrapLongWord(word, currentLine);
            }
            if (currentLine.length() + word.length() <= MAX_TEXT_LEN) {
                currentLine.append(word).append(" ");
            } else {
                wrappedText.append(currentLine.toString().trim()).append("\n");
                currentLine = new StringBuilder(word + " ");
            }
        }

        if (currentLine.length() > 0) {
            wrappedText.append(currentLine.toString().trim()).append("\n");
        }
    }

    private static void wrapLongWord(String word, StringBuilder wrappedText) {
        int startIndex = 0;
        while (startIndex < word.length()) {
            int endIndex = Math.min(startIndex + MAX_TEXT_LEN, word.length());
            String subWord = word.substring(startIndex, endIndex);
            wrappedText.append(subWord).append("\n");
            startIndex = endIndex;
        }
    }
}

