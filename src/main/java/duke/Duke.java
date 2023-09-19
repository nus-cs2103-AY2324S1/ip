package duke;

import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


/**
 * The Duke class is the main class of the Duke chatbot application.
 * It initializes the application and interacts with the user
 * through the command-line interface.
 */
public class Duke {

    /**
     * The TaskList that stores the tasks in the application.
     */
    public static TaskList taskList = new TaskList();

    public Duke() {
        // ...
    }


//    /**
//     * The main method of the Duke chatbot application.
//     *
//     * @param args The command-line arguments (not used in this application).
//     * @throws DukeException If there is an issue with Duke's operation.
//     */
//    public static void main(String[] args) {
//        // Create the data location for storing task data
//        Storage storage = new Storage();
//        Storage.createDataLocation();
//        // Load tasks from the data file into the TaskList
//        storage.loadTasksFromFile(taskList);
//        List<Task> allTasks = taskList.getTasks();
//        // Create a scanner to read user input
//        Scanner sc = new Scanner(System.in);
//        // Create a parser to process user commands
//        Parser parser = new Parser();
//        // Create a user interface
//        Ui ui = new Ui();
//        if (allTasks.size() == 0) {
//            parser.run = 1;
//        }
//        String task = "";
//        ui.greet();
//        System.out.flush();
//        parser.parse(sc);
//        ui.sayBye();
//    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
//       return "Duke heard: " + input;
            return Parser.parse(input);
    }
}



