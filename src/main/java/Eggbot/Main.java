package Eggbot;

import static Eggbot.Eggbot.initializeBot;
import static Ui.printers.Printer.initializePrinter;

import java.io.IOException;

import Ui.javafx.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Eggbot eggbot = new Eggbot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setEggbot(eggbot);
            stage.show();
            initializePrinter(fxmlLoader.getController());
            initializeBot();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Application launched successfully!");
    }
}
