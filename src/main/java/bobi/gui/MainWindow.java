package bobi.gui;

import java.util.Timer;
import java.util.TimerTask;

import bobi.Bobi;
import bobi.utility.Ui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * MainWindow class is a controller for MainWindow.
 * It provides the layout for the other controls.
 *
 * @author ruo-x
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Bobi bobi;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image bobiImage = new Image(this.getClass().getResourceAsStream("/images/Bobi.png"));

    /**
     * Initialize the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Initialize Bobi, Bobi greets the user.
     *
     * @param d Bobi to initialize.
     */
    public void setBobi(Bobi d) {
        bobi = d;
        dialogContainer.getChildren().add(
                DialogBox.getBobiDialog(Ui.greeting(), bobiImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bobi's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bobi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBobiDialog(response, bobiImage)
        );

        // if user input is "bye", GUI closes automatically after 1 second.
        if (input.equalsIgnoreCase("bye")) {
            handleUserExit();
        }

        userInput.clear();
    }

    /**
     * Automatically exits GUI after 1 second.
     */
    private void handleUserExit() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                timer.cancel();
            }
        }, 1000);
    }

    /**
     * Shows reminder notification when users first launch the GUI.
     * This notification reminds users of their overdue tasks,
     * and tasks that are due the coming week.
     */
    public void showReminder() {
        ImageView bobiView = new ImageView();
        bobiView.setImage(bobiImage);
        bobiView.setFitWidth(100);
        bobiView.setPreserveRatio(true);
        bobiView.setSmooth(true);
        bobiView.setCache(true);

        Notifications reminder = Notifications.create();
        reminder.graphic(bobiView);
        reminder.title("Bobi Reminder!");
        reminder.text(bobi.getReminder());
        reminder.hideAfter(new Duration(30000));
        reminder.position(Pos.CENTER);

        reminder.show();
    }
}
