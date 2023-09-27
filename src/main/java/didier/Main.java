package didier;

import didier.controller.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Main application class that provides the basic functionality to
 * set the scene on the stage and render the window.
 */
public class Main extends Application {

    private Didier didier = new Didier("data/", "didier.txt");
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainWindow mainWindow = new MainWindow(primaryStage);
        Scene scene = new Scene(mainWindow);
        primaryStage.setScene(scene);
        mainWindow.setDidier(didier);
        primaryStage.show();
    }
}
