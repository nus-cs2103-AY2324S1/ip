package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private MainWindow mainWindow;
    private Duke duke;

    @Override
    public void start(Stage stage) {
        String filename = showFilenameDialog(stage);
        if (filename != null && !filename.isEmpty()) {
            duke = new Duke(filename);

            try {
                initializeMainWindow(stage);
            } catch (IOException e) {
                System.err.println("Error initializing main window.");
                e.printStackTrace();
            }
        } else {
            System.out.println("No filename provided. Exiting application.");
            stage.close();
        }
    }

    private String showFilenameDialog(Stage parentStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/FilenameDialog.fxml"));
            AnchorPane root = fxmlLoader.load();
            FilenameDialogController controller = fxmlLoader.<FilenameDialogController>getController();
            controller.populateFilenamesToListView();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Enter Filename");
            dialogStage.initOwner(parentStage);
            dialogStage.setScene(new Scene(root));
            controller.setStage(dialogStage);

            dialogStage.showAndWait();

            return controller.getFilename();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void initializeMainWindow(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        AnchorPane ap = fxmlLoader.load();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        mainWindow = fxmlLoader.<MainWindow>getController();
        mainWindow.setDuke(duke);
        duke.activate(mainWindow);
        stage.show();
    }

}
