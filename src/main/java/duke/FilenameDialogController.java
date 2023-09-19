package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Represents a dialog box controller that allows users to:
 * - View a list of available filenames.
 * - Submit a chosen filename.
 */
public class FilenameDialogController {
    @FXML
    private TextField filenameField;

    @FXML
    private ListView<String> filenameListView;

    private Stage dialogStage;
    private String selectedFilename;

    /**
     * Sets the stage for this dialog.
     *
     * @param stage The stage of this dialog.
     */
    public void setStage(Stage stage) {
        this.dialogStage = stage;
    }

    /**
     * Submits the filename input by the user.
     */
    @FXML
    private void submitFilename() {
        selectedFilename = filenameField.getText().trim();

        if (selectedFilename.isEmpty()) {
            showAlert("Error", "Filename cannot be empty!");
        } else {
            dialogStage.close();
        }
    }

    /**
     * Displays an alert dialog with the specified title and message.
     *
     * @param title   The title of the alert.
     * @param message The message to be displayed in the alert.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Returns the filename chosen by the user.
     *
     * @return The chosen filename.
     */
    public String getFilename() {
        return selectedFilename;
    }

    /**
     * Populates the ListView with filenames found in the "./data/" directory.
     */
    void populateFilenamesToListView() {
        Path directoryPath = Paths.get("./data/");

        if (Files.exists(directoryPath) && Files.isDirectory(directoryPath)) {
            try (Stream<Path> filePaths = Files.walk(directoryPath, 1)) {
                filePaths
                        .filter(Files::isRegularFile)
                        .filter(path -> path.toString().endsWith(".txt"))
                        .forEach(path -> {
                            String filenameWithoutExtension = removeFileExtension(path.getFileName().toString());
                            filenameListView.getItems().add(filenameWithoutExtension);
                        });
            } catch (IOException e) {
                System.err.println("Error populating filenames: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Removes the file extension from the provided filename.
     *
     * @param filename The filename with the extension.
     * @return The filename without its extension.
     */
    private String removeFileExtension(String filename) {
        return filename.substring(0, filename.length() - 4); // Removes the ".txt" extension.
    }
}
