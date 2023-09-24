package eggbot;

import static eggbot.Eggbot.initializeBot;
import static ui.printers.Printer.initializePrinter;

import java.awt.*;
import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import ui.javafx.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Eggbot eggbot = new Eggbot();

    @Override
    public void start(Stage stage) {
        try {
            // Obtain dimensions of user's screen
            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            int winWidth = gd.getDisplayMode().getWidth();
            int winHeight = gd.getDisplayMode().getHeight();

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/mainwindow/MainWindow.fxml"));
            BorderPane bp = fxmlLoader.load();
            Scene scene = new Scene(bp, winWidth * 0.4, winHeight * 0.6);
            stage.getIcons().add(new Image("images/egg.png"));
            stage.setTitle("Eggbot - Your task-managing egg");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setEggbot(eggbot);
            stage.show();

            // Initializes the printer and finally, the bot
            initializePrinter(fxmlLoader.getController());
            initializeBot();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Application launched successfully!");
    }
}
