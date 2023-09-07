package duke.uiux;
import java.util.Scanner;

import duke.Duke;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

/**
 * Encapsulates the UI of the chat bot.
 */
public class Ui extends Application {
    private static final String FAREWELL = "Bye. Hope to see you again soon!";
    private static final String GREETINGS = "Hello! I'm AChatBot\n"
            + "What can I do for you?";

    private Scanner scanner;
    private Stage window; 
    private Duke duke;
    
    public void showException(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Gets input from user.
     */
    public String getNextLine() {
        return scanner.nextLine();
    }

    private void greet() {
        System.out.println(GREETINGS);
    }

    /** 
     * Terminates the Ui.
     */
    public void close() {
        System.out.println(FAREWELL);
        this.scanner.close();
    }

    /**
     * Reopens the Ui.
     */
    public void open() {
        this.greet();
        this.scanner = new Scanner(System.in);
    }

    public void print(String s) {
        System.out.println(s);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.duke = new Duke();
        this.greet();
        this.window = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader(Ui.class.getResource("./MainWindow.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Scene mainScene = new Scene(anchorPane);
        fxmlLoader.<MainWindow>getController().setDuke(duke);
        try {
            window.setScene(mainScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.setTitle("AChatBot");
        window.show();
    }   

}
