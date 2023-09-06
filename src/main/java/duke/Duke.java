package duke;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application{

    public Duke() throws Exception{
        String filePath = "/Users/william/Desktop/ip/src/main/java/data/zenith.txt";
        try {
            LoadFile loadFile = new LoadFile(filePath);
            loadFile.load();
            Ui ui = new Ui();
            ui.greet();
            TaskList taskList = new TaskList();
            taskList.Answer();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    public static void main(String[] args) throws Exception {

        Duke duke = new Duke();
    }
}
