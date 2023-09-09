package pardiyem.ui;

import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pardiyem.parser.Parser;
import pardiyem.storage.Storage;
import pardiyem.task.TaskList;

public class Ui {
    static final String WELCOME = "Salve, I'm Pardi\nWhat can I do for you?";
    static final String BYE = "Ciao! See you again!";
    private final Scanner scanner;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Ui() throws IOException {
        scanner = new Scanner(System.in);
    }

    public void showGreeting() {
        showDivider();
        System.out.println(WELCOME);
        showDivider();
    }

    public void showFarewell() {
        System.out.println(BYE);
    }

    public void showDivider() {
        System.out.println("-------------------------");
    }

    public void showOutput(String s) {
        System.out.println(s);
    }

    public String readCommand() throws IllegalArgumentException {
        if (!scanner.hasNext()) {
            throw new IllegalArgumentException("Out of commands, are we?");
        }
        return scanner.nextLine();
    }

    public void showException(Exception e) {
        System.out.println(e.toString());
    }
}
