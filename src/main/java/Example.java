//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class Example extends Application {
////    private ScrollPane scrollPane;
////    private VBox dialogContainer;
////    private TextField userInput;
////    private Button sendButton;
////    private Scene scene;
////
////    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
////    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
////
////    @Override
////    public void start(Stage stage) {
////        // Adding nodes
////        scrollPane = new ScrollPane();
////        dialogContainer = new VBox();
////        scrollPane.setContent(dialogContainer);
////
////        userInput = new TextField();
////        sendButton = new Button("Send");
////
////        AnchorPane mainLayout = new AnchorPane();
////        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
////
////        scene = new Scene(mainLayout);
////
////        stage.setScene(scene); // Setting the stage to show our screen
////        stage.show(); // Render the stage.
////    }
////
////    protected String getResponse(String input) {
////        return "Duke heard: " + input;
////    }
//}
