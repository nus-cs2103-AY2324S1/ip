package peko;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Peko extends Application {
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
    public void start(Stage stage) throws Exception {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

}
