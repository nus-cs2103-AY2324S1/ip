package peko;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Peko extends Application implements EventHandler<ActionEvent> {
    private UserInputHandler userInputHandler;
    private UI ui;
    private StorageHandler storageHandler;
    public static void main(String[] args) {

        Application.launch(GUIController.class, args);
        new Peko().run();
    }
    private void run() {
        Output.intro();
        while (true) {
            userInputHandler.newInput();
            if (!userInputHandler.processInput()) {
                break;
            }
            SaveHandler.saveTo();
        }
        Output.exit();

    }

    public String getResponse(String s) {
        userInputHandler.newInput(s);
        return userInputHandler.getResponse();
    }
    public Peko() {
        userInputHandler = new UserInputHandler();
        storageHandler = new StorageHandler();
    }



    @Override
    public void start(Stage stage) {
        Application.launch(GUIController.class);
    }

    @Override
    public void handle(ActionEvent event) {

    }
}
