package sana;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Sana sana = new Sana("data/SanaTasks.txt");

    @Override
    public void start(Stage stage) {
        try {
//            String filePath = "data/SanaTasks.txt";
//            File folder = new File(String.valueOf(Paths.get("data/SanaTasks.txt").getParent()));
//
//            // Check if folder exists, if not create one
//            if (!folder.exists()) {
//                folder.mkdirs();
//            }
//
//            // Check if file exists, if not create one
//            File file = new File(filePath);
//            if (!file.exists()) {
//                try {
//                    file.createNewFile();
//                } catch (IOException e) {
//                    System.out.println(e.getMessage());
//                }
//            }

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Sana: Your Task Manager");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSana(sana);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
