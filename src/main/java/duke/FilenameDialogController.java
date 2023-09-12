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
 * This control represents a dialog box consisting of a list view
 * to display the available file names as well as a text box
 * to allow user to submit filename
 */
public class FilenameDialogController {
    @FXML
    private TextField filenameField;

    @FXML
    private ListView<String> filenameListView;

    private Stage stage;
    private String filename;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void submitFilename() {
        filename = filenameField.getText().trim();

        if (filename.isEmpty()) {
            showAlert("Error", "Filename cannot be empty!");
        } else {
            stage.close();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public String getFilename() {
        return filename;
    }

    void populateFilenamesToListView() {
        Path dirPath = Paths.get("./data/");

        // Ensure the directory exists
        if (Files.exists(dirPath) && Files.isDirectory(dirPath)) {
            try (Stream<Path> paths = Files.walk(dirPath, 1)) {
                // "1" to ensure only one level depth
                paths.filter(Files :: isRegularFile)
                        .filter(path -> path.toString().endsWith(".txt"))
                        // Only .txt files
                        .forEach(path -> {
                            String filename = path.getFileName().toString();
                            filenameListView.getItems().add(filename.substring(0, filename.length() - 4));
                            // remove the .txt extension
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
