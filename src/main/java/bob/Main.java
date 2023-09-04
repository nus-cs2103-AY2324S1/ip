package bob;

import bob.controller.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main Class that setups and initialises the stage and primary scene
 * of the JavaFX GUI of this application.
 */
public class Main extends Application {
    private Bob bob = new Bob();

    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setBob(this.bob);
        mainWindow.setStage(stage);
        mainWindow.initialise();

        stage.setTitle("It's Bob");
        Scene scene = new Scene(mainWindow);
        stage.setScene(scene);
        stage.show();
    }
}
