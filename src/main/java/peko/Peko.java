package peko;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class Peko extends Application implements EventHandler<ActionEvent> {
    private UserInputHandler userInputHandler;
    private UI ui;
    private StorageHandler storageHandler;
    public static void main(String[] args) {
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
    public Peko() {
        userInputHandler = new UserInputHandler();
        storageHandler = new StorageHandler();
    }



    @Override
    public void start(Stage primaryStage) throws Exception {

    }


    @Override
    public void handle(ActionEvent event) {

    }
}
