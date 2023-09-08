package ruiz;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;

import ruiz.command.Command;
import ruiz.exception.BotException;
import ruiz.task.TaskList;

/**
 * Ruiz is a task management chatbot.
 */
public class Ruiz {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private static String filePath = "tasks.txt";
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor for the Ruiz class.
     */
    public Ruiz() {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            ui.unableToLoadFile();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot.
     */
    public String getResponse(String input) {
        while (true) {
            String message = "";
            try {
                Command command = parser.getCommand(input);
                switch (command) {
                case BYE:
                    message = ui.printBye();
                    break;
                case LIST:
                    message = ui.getTasks(this.tasks.getTaskList());
                    break;
                case MARK:
                    message = this.tasks.markTask(input);
                    break;
                case UNMARK:
                    message = this.tasks.unmarkTask(input);
                    break;
                case DELETE:
                    message = this.tasks.deleteTask(input);
                    break;
                case DEADLINE:
                    message = this.tasks.addDeadline(input);
                    break;
                case TODO:
                    message = this.tasks.addTodo(input);
                    break;
                case EVENT:
                    message = this.tasks.addEvent(input);
                    break;
                case FIND:
                    message = this.tasks.findTasksWithKeyword(input);
                    break;
                case UNKNOWN:
                    throw new BotException(ui.botErrorMsg());
                default:
                }
                this.storage.saveTasks(this.tasks.getTaskList());
            } catch (BotException e) {
                System.out.println(e);
            } catch (IOException e) {
                message = ui.unableToSaveTask();
            } catch (DateTimeParseException e) {
                message = ui.wrongFormat();
            }
            return message;
        }
    }
}
