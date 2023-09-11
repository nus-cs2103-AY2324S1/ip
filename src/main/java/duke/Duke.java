package duke;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;




public class Duke extends Application{

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private String input;

    LoadFile loadFile;
    TaskList taskList;

    int first = 1;


    private void handleUserInput() {
        String userInputText = userInput.getText();
        //String dukeResponse = getResponse();
        DialogBox userDialog = DialogBox.getUserDialog("b", user);
        DialogBox dukeDialog = DialogBox.getDukeDialog("a", duke);
        dialogContainer.getChildren().addAll(userDialog, dukeDialog);
        userInput.clear();
    }
    public Duke(String input) {
        this.input = input;
    }

    public Duke() throws Exception {
        try {
            String filePath = "/Users/william/Desktop/ip/src/main/java/data/zenith.txt";
            loadFile = new LoadFile(filePath); //soutd to string
            TaskList lst = new TaskList();
            System.out.println(000);
            System.out.println(lst.getList().size());
            loadFile.load();
        } catch (Exception e) {
            System.out.println(e);
        }

    }



    @Override
    public void start(Stage stage) throws Exception{
        try {
            //Step 1. Formatting the window to look as expected.

            //...
            scrollPane = new ScrollPane();
            dialogContainer = new VBox();
            scrollPane.setContent(dialogContainer);

            userInput = new TextField();
            sendButton = new Button("Send");

            AnchorPane mainLayout = new AnchorPane();
            mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

            scene = new Scene(mainLayout);

            stage.setScene(scene);
            stage.show();

            //Step 2. Formatting the window to look as expected
            stage.setTitle("Duke");
            stage.setResizable(false);
            stage.setMinHeight(600.0);
            stage.setMinWidth(400.0);

            mainLayout.setPrefSize(400.0, 600.0);

            scrollPane.setPrefSize(385, 535);
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

            scrollPane.setVvalue(1.0);
            scrollPane.setFitToWidth(true);

            // You will need to import `javafx.scene.layout.Region` for this.
            dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

            userInput.setPrefWidth(325.0);

            sendButton.setPrefWidth(55.0);

            AnchorPane.setTopAnchor(scrollPane, 1.0);

            AnchorPane.setBottomAnchor(sendButton, 1.0);
            AnchorPane.setRightAnchor(sendButton, 1.0);

            AnchorPane.setLeftAnchor(userInput , 1.0);
            AnchorPane.setBottomAnchor(userInput, 1.0);

            sendButton.setOnMouseClicked((event) -> {
                handleUserInput();
            });
            userInput.setOnAction((event) -> {
                handleUserInput();
            });
            dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
            String filePath = "/Users/william/Desktop/ip/src/main/java/data/zenith.txt";
            loadFile = new LoadFile(filePath); //soutd to string
            TaskList lst = new TaskList();
            loadFile.load();
            System.out.println(000);
            System.out.println(lst.getList().size());

        } catch (Exception e) {
            System.out.println(e);
        }



        // more code to be added here later
    }



    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
    // make getResponse to take this.input instead of arg, thus need to modify input from other class, need to make sure other widgets
    // can modify this string
    public String getResponse() throws Exception{
        try {

            taskList = new TaskList();
            System.out.println(taskList.getList().size());
            String output = taskList.Answer(input);

            System.out.println(first);

            return output;




        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";

    }





    public static void main(String[] args) throws Exception {

        //Duke duke = new Duke();

    }
}
