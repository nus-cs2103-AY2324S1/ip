package duke;
import dukeUiElements.Ui;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke extends Application{
    private Stage mainStage;
    private Scene scene;
    private ScrollPane scrollPane;
    private TextField userInput;
    private Button sendButton;
    private VBox vBox;
    private Ui ui;
    public static Path pathOfDirectory = Paths.get("./data/duke.txt");
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Duke() {
        ui = new Ui ();
        try {
            Storage.readFromDisk(pathOfDirectory, TaskList.getStoreTask());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs the program with a while loop.
     */
//    public void run() {
//        ui.showGreeting();
//        Scanner userInputObject = new Scanner(System.in);
//        while (true) {
//            ui.userInputUsher();
//            String userInput = userInputObject.nextLine();
//
//            try {
//                boolean isBreak = Parser.parse(userInput);
//                if (!isBreak) {
//                    break;
//                }
//            } catch (NumberFormatException e) {
//                Ui.errorPrint(Ui.indent + "Invalid character input");
//            } catch (DukeException e) {
//                Ui.errorPrint(Ui.indent + "Error: " + e.getMessage());
//            } catch (IndexOutOfBoundsException e) {
//                Ui.errorPrint(Ui.indent + "Invalid entry / Task not in list... Please try again...");
//            } catch (IllegalArgumentException e) {
//                Ui.errorPrint("☹ OOPS!!! Sorry, but i do not know what that means :-(");
//            }
//        }
//    }
    @Override
    public void start(Stage mainStage) {
        mainStage.setTitle("Tom!");
        scrollPane = new ScrollPane();
        vBox = new VBox();
        scrollPane.setContent(vBox);
        userInput = new TextField();
        sendButton = new Button("Send");
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(scrollPane, userInput, sendButton); //Basic interface with scrollbar and sendbutton.

        scene = new Scene(anchorPane);  //add anchorpane to the scene
        mainStage.setScene(scene);
        mainStage.show();

        mainStage.setTitle("Tom!");
        mainStage.setResizable(false);
        mainStage.setMinHeight(600.0);
        mainStage.setMinWidth(400.0);

        anchorPane.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        vBox.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        vBox.getChildren().add(DialogBox.greetingDialogBox(new ImageView(duke)));

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            vBox.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            vBox.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        vBox.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        vBox.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        try {
            String dukeResponse = Parser.parse(input);
            if (dukeResponse == "bye") {
                return "Bye. Hope to see you again soon!";
            } else {
                return dukeResponse;
            }
        } catch (DukeException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return Ui.indent + "Invalid character input";
        } catch (IndexOutOfBoundsException e) {
            return Ui.indent + "Invalid entry / Task not in list... Please try again...";
        } catch (IllegalArgumentException e) {
            return "OOPS!!! Sorry, but i do not know what that means :-(";
        }

    }

}