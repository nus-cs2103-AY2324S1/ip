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

        if (filename != null && !filename.trim().isEmpty()) {
            duke = new Duke(filename);

            try {
                initializeMainWindow(stage);
            } catch (IOException e) {
                System.err.println("Error initializing main window: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("No filename provided. Exiting application.");
            stage.close();
        }
    }

    /**
     * Show a dialog to the user to choose a filename.
     *
     * @param parentStage The parent stage of the dialog.
     * @return The selected filename.
     */
    private String showFilenameDialog(Stage parentStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/FilenameDialog.fxml"));
            AnchorPane root = fxmlLoader.load();
            FilenameDialogController controller = fxmlLoader.<FilenameDialogController>getController();

            controller.populateFilenamesToListView();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Dong");
            dialogStage.initOwner(parentStage);
            dialogStage.setScene(new Scene(root));
            controller.setStage(dialogStage);

            dialogStage.showAndWait();

            return controller.getFilename();
        } catch (IOException e) {
            System.err.println("Error showing filename dialog: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Initializes and displays the main application window.
     *
     * @param stage The primary stage of the application.
     * @throws IOException If there's an issue initializing the window.
     */
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
