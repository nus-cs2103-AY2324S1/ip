//import javafx.application.Application;
//import javafx.fxml.FXML;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.Region;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class VedaGui extends Application {
//    @FXML
//    private ScrollPane scrollPane;
//    @FXML
//    private VBox dialogContainer;
//    @FXML
//    private TextField userInput;
//    @FXML
//    private Button sendButton;
//    @FXML
//    private Scene scene;
//
//    private Image Veda = new Image(this.getClass().getResourceAsStream("/images/Veda.png"));
//    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
//
//    @Override
//    public void start(Stage primaryStage) {
//        //Sets up
//        setUpNodes();
//        Region mainLayout = setUpLayout();
//        setUpStage(primaryStage);
//        insertFunctionality();
//
//        this.scene = new Scene(mainLayout);
//        primaryStage.setScene(scene); //Setting the stage to show scene
//
//        primaryStage.show(); //Render the stage
//    }
//
//    @FXML
//    /**
//     * Iteration 1:
//     * Creates a label with the specified text and adds it to the dialog container.
//     *
//     * @param text String containing text to add.
//     * @return a label with the specified text that has word wrap enabled.
//     */
//    private Label getDialogLabel(String text) {
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//
//        return textToAdd;
//    }
//
//    @FXML
//    /**
//     * Adds functionality to handle user input
//     */
//    private void insertFunctionality() {
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//
//        //Scroll down to the end every time dialogContainer's height changes
//        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));
//    }
//
//    @FXML
//    /**
//     * Iteration 2 and 3:
//     * Creates two dialog boxes, one echoing the user input and the other containing Veda's reply and then
//     * appends them to the dialog container. Clears the user input after processing.
//     */
//    private void handleUserInput() {
//        //TODO link to Veda logic
//        if (userInput.getText().equals("")) {
//            return;
//        }
//
//        //Create Label nodes to show the enter-ed texts
//        Label userText = getDialogLabel(userInput.getText());
//        Label vedaText = getDialogLabel(getResponse(userInput.getText()));
//
//        //Update dialogContainer
//        dialogContainer.getChildren().addAll(
////                DialogBox.getUserDialog(userText, new ImageView(this.user)),
////                DialogBox.getDukeDialog(vedaText, new ImageView(this.Veda))
//                DialogBox.getUserDialog(userInput.getText(), this.user),
//                DialogBox.getDukeDialog(getResponse(userInput.getText()), this.Veda)
//        );
//
//        //Clear the userInput for next input
//        userInput.clear();
//    }
//
//    /**
//     * Sets up and format the different nodes that form the GUI
//     */
//    private void setUpNodes() {
//        this.scrollPane = new ScrollPane();
//        this.dialogContainer = new VBox();
//        this.scrollPane.setContent(dialogContainer);
//
//        this.userInput = new TextField();
//        sendButton = new Button("Send");
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//    }
//
//    /**
//     * Prepares the main layout for us to use in our scene.
//     * @return a Region that composes the different nodes of our GUI.
//     */
//    private Region setUpLayout() {
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton,1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput, 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        return mainLayout;
//    }
//}
//
