package duke.taskmanagement;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {
    String PATH = "./data/duke.txt";

    private Ui ui = new Ui();
    private TaskList tasks;
    private Storage storage = new Storage(PATH);
    public void run() {
        ui.greet();
        tasks = new TaskList(this.ui, storage.loadData(), storage);
        String cmd = this.ui.readInCmd();
        Parser parser = new Parser(this.ui, this.tasks);
        parser.readCmd(this.tasks, cmd);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        ui.greet();
        tasks = new TaskList(this.ui, storage.loadData(), storage);
        String cmd = this.ui.readInCmd();
        Parser parser = new Parser(this.ui, this.tasks);
        parser.readCmd(this.tasks, input);
        return "Duke heard: " + input;
    }
    public static void main(String[] args) {
        //new Duke().run();
        Launcher.main(args);
    }
}
