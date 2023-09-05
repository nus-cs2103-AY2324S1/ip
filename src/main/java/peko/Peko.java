package peko;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class Peko extends Application implements EventHandler<ActionEvent> {
    private UserInputHandler userInputHandler;
    private UI ui;
    private StorageHandler storageHandler;
    public static void main(String[] args) {
        new Peko().run();
    }
    private void run() {
        Output.intro();
        while (true) {
            userInputHandler.newInput();
            if (!userInputHandler.processInput()) {
                break;
            }
            SaveHandler.saveTo();
        }
        Output.exit();

    }
    public Peko() {
        userInputHandler = new UserInputHandler();
        storageHandler = new StorageHandler();
    }

    private Button button;
    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("PekoBot!");
        button = new Button("Enter Peko!");
        button.setOnAction(this);

        StackPane layout = new StackPane();
        layout.getChildren().add((button));
        Scene scene = new Scene(layout, 300, 250);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == button) {
            System.out.println("Konpeko");
        }
    }

}
