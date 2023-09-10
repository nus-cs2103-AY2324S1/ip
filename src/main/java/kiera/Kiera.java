package kiera;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



/**
 * Main class for the Kiera application. Kiera is a task management application
 * that allows users to manage tasks, including adding, deleting, marking as done,
 * and listing tasks.
 */
public class Kiera extends Application {

    protected static String filePath = "./data/storage.txt";
    private Storage storage;
    private Ui ui;
    private TaskList tasks;


    /**
     * Constructor for Kiera.
     */
    public Kiera() {
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Kiera.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setKiera();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//    public void run() {
//        ui.showHello();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                ui.showLine();
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (KieraException e) {
//                ui.showError(e.toString());
//            } finally {
//                ui.showLine();
//            }
//        }
//    }

