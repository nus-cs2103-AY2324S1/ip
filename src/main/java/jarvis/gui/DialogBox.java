package jarvis.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    private DialogBox(String message, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/views/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        buildDialogBox(message, img);
    }

    /**
     * Creates a user dialog box.
     *
     * @param text The text content of the user dialog box.
     * @param img  The image to be displayed for the user dialog box.
     * @return A DialogBox representing the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a Jarvis dialog box.
     *
     * @param text The text content of the Jarvis dialog box.
     * @param img  The image to be displayed for the Jarvis dialog box.
     * @return A DialogBox representing Jarvis's dialog.
     */
    public static DialogBox getJarvisDialog(String text, Image img) {
        var db = new DialogBox(text, img);
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
    private void buildDialogBox(String message, Image img) {
        Text formattedText = createText(message);
        Circle circleImage = getCircularImg(img);
        StackPane dialogBox = createDialogBox(formattedText);
        this.getChildren().addAll(dialogBox, circleImage);
    }

    private Text createText(String string) {
        assert string != null : "String to be converted cannot be null";
        return wrapText(new Text(string));
    }

    /**
     * Section on text wrapping inspired by @tjingsheng
     * https://github.com/tjingsheng/
     */
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

    private static Circle getCircularImg(Image img) {
        Circle circleImage = new Circle(0, 0, PFP_SIZE);
        circleImage.setFill(new ImagePattern(img));
        return circleImage;
    }

    private StackPane createDialogBox(Text text) {
        Rectangle messageBox = createMessageBox(text);
        return new StackPane(messageBox, text);
    }

    private Rectangle createMessageBox(Text formattedText) {
        assert formattedText != null : "Text cannot be null.";
        Rectangle messageBox = createCustomMessageBox(formattedText);
        styleMessageBox(messageBox);
        return messageBox;
    }

    private static Rectangle createCustomMessageBox(Text formattedText) {
        double messageBoxWidth = formattedText.getBoundsInLocal().getWidth() + 80;
        double messageBoxHeight = formattedText.getBoundsInLocal().getHeight() + 20;
        return new Rectangle(messageBoxWidth, messageBoxHeight);
    }

    private static void styleMessageBox(Rectangle messageBox) {
        messageBox.setFill(Color.WHITE);
        messageBox.setArcWidth(ARC_VALUE);
        messageBox.setArcHeight(ARC_VALUE);
    }
}

