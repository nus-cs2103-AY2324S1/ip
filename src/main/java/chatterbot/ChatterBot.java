package chatterbot;

import chatterbot.data.Task;
import chatterbot.data.TaskList;
import chatterbot.parser.Parser;
import chatterbot.storage.Storage;
import chatterbot.ui.Ui;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the chatbot that is able to maintain a list of tasks.
 */
public class ChatterBot {

    protected static ArrayList<Task> list = new ArrayList<>();
    protected static String file = "data/ChatterBot.txt";
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public ChatterBot(TaskList taskList) {
        this.taskList = taskList;
        this.ui = new Ui(taskList.list);
        this.storage = new Storage(file, taskList.list);
    }

    /**
     * This is the main method which executes the ChatterBot program.
     * @param args Unused.
     */

    public static void main(String[] args) {

        Ui ui = new Ui(list);
        Storage storage = new Storage(file, list);
        TaskList taskList = new TaskList(list);

        ui.showWelcomeMessage();

        File f = new File(file);
        File folder = f.getParentFile();
        if (!folder.exists()) {
            System.out.println("Error! No data folder found");
        }

        taskList.initiateTaskList(storage);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userMessage = scanner.nextLine();
            Parser.evaluateCommand(userMessage, ui, list, storage, file, taskList);
        }
    }

    //EDIT TO SUIT MY OWN
    public String getResponse(String input) {
        return Parser.evaluateCommand(input, ui, list, storage, file, taskList);
    }
}