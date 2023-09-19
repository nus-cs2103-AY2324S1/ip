package catbot.io;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Application that creates a JavaFX UI.
 */
public class CatBotJavaFxApplication extends Application {

    private static CatbotJavaFxController lastCreatedController;

    static CatbotJavaFxController getLastCreatedController() {
        return lastCreatedController;
    }

    @Override
    public void start(Stage stage) {
        // https://se-education.org/guides/tutorials/javaFx.html
        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(CatBotJavaFxApplication.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            lastCreatedController = fxmlLoader.getController();
            stage.show();
            CatBotJavaFxIo.getLastApplicationLaunchPoint().initializeAfterFxml();
        } catch (IOException ignored) { //noinspection UnnecessarySemicolon
            ;
        }
    }

}
