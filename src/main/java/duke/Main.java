package duke;

import java.io.IOException;

import duke.exception.DukeException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private static Duke duke = new Duke("data/duke.txt");
    private static VBox dialogContainer;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainView.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("/styles/palette.css").toExternalForm());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Roe");
            stage.show();

            dialogContainer = (VBox) scene.lookup("#dialogContainer");
            TextField userInput = (TextField) scene.lookup("#userInput");
            ScrollPane scrollPane = (ScrollPane) scene.lookup("#scrollPane");

            dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

            userInput.setOnAction((event) -> {
                String userInputText = userInput.getText();
                outputDialog(userInputText, true);
                userInput.clear();

                try {
                    Parser.processInput(userInputText, duke);
                } catch (DukeException e) {
                    outputDialog(e.getMessage(), false);
                }
            });

            outputDialog("Hello! I'm Roe!\n" + "What can I do for you?", false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Outputs a string into the GUI.
     * @param text The text to be outputted.
     * @param isUser Decides whether the output is to be printed on the LHS or RHS.
     */
    public static void outputDialog(String text, boolean isUser) {
        HBox newDialog = formatDialog(text, isUser);
        dialogContainer.getChildren().add(newDialog);
    }

    /**
     * Outputs a string into the GUI. The source is assumed to be Duke.
     * @param text The text to be outputted.
     */
    public static void outputDialog(String text) {
        outputDialog(text, false);
    }

    private static HBox formatDialog(String text, boolean isUser) {
        Label textToAdd = new Label(text);
        textToAdd.setFont(new Font("Calibri Light", 18));
        textToAdd.getStyleClass().add("dialog");
        textToAdd.setWrapText(true);
        textToAdd.setMaxWidth(250);

        HBox dialogBox = new HBox(textToAdd);

        if (isUser) {
            dialogBox.setAlignment(Pos.TOP_RIGHT);
        } else {
            dialogBox.setAlignment(Pos.TOP_LEFT);
        }

        return dialogBox;
    }
}
