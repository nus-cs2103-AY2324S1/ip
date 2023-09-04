package bob;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;



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
