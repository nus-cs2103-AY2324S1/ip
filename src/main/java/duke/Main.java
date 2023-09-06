package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The Main class serves as the primary entry point for the Duke application.
 * process for the application's Command-Line Interface (CLI).
 */
public class Main extends Application {
    private Duke changooseBot;

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param stage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            VBox rootLayout = fxmlLoader.load();
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            MainWindow mainWindowController = fxmlLoader.getController();
            changooseBot = new Duke();
            mainWindowController.setDuke(changooseBot);
            String startUpMessage = changooseBot.initStorage();
            mainWindowController.addMessageFromDuke(startUpMessage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
