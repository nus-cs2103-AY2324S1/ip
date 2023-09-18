package catbot.io;

import java.io.IOException;

import catbot.bot.CatBot;
import catbot.task.TaskList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CatbotJavaFxIo extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(CatbotJavaFxIo.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<CatbotJavaFxController>getController().setCatBot(new CatBot(new TaskList("Tasks.txt")));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}