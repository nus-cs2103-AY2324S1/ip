package duke;

import Exceptions.DukeArgumentException;
import Exceptions.DukeException;

import javafx.application.Application;
import javafx.fxml.FXML;
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

import java.io.IOException;
import java.util.Scanner;

/**
 * This class represents the main program.
 */
public class Duke {
    private static Storage storage;
    private static Ui ui;
    private static Parser parser;
    private static TaskList tasks;
    private static String DIR_PATH = "./data";
    private static String FILE_PATH = "./data/duke.txt";
    enum Command { MARK, UNMARK, DELETE, TODO, EVENT, DEADLINE, BYE, LIST, INVALID, FIND }
    /**
     * Checks if the command is valid or invalid.
     * @param command Command word to be identified.
     * @return The command word in type Command.
     */
    public static Command commandCheck(String command) {
        switch(command) {
        case "bye":
            return Command.BYE;
        case "unmark":
            return Command.UNMARK;
        case "mark":
            return Command.MARK;
        case "delete":
            return Command.DELETE;
        case "list":
            return Command.LIST;
        case "todo":
            return Command.TODO;
        case "deadline":
            return Command.DEADLINE;
        case "event":
            return Command.EVENT;
        case "find":
            return Command.FIND;
        default:
            return Command.INVALID;
        }
    }
    /**
     * Implements the action of the command.
     * @param command Command word of the task.
     * @param info Details of the task.
     * @return The message to acknowledge the success of the implementation of task.
     */
    public static String printCommand(Command command, String info)
            throws DukeException, IOException, DukeArgumentException {
        switch(command) {
        case BYE:
            return ui.exit();
            case UNMARK:
            return tasks.unmark(info);
        case MARK:
            return tasks.mark(info);
        case DELETE:
            return tasks.deleteTask(info);
        case LIST:
            return tasks.listTask();
        case TODO:
            return tasks.todoTask(info);
        case EVENT:
            return tasks.eventTask(info);
        case DEADLINE:
            return tasks.deadlineTask(info);
        case FIND:
            return tasks.findTask(info);
        default:
            throw new DukeException("     OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
    /**
     * Empty constructor method for Duke.
     */
    public Duke() {
        this.storage = new Storage(DIR_PATH, FILE_PATH);
        ui = new Ui();
        parser = new Parser();
        tasks = new TaskList(storage.loadTask(), storage);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    @FXML
    String getResponse(String input) {
        String[] findCommand = parser.commandSplit(input);
        Command order = commandCheck(findCommand[0]);
        String response;
        try {
            response = printCommand(order, input);
            storage.saveTask(tasks);
        } catch (DukeException | DukeArgumentException message) {
            response = message.getMessage();
        } catch (IOException e) {
            response = "     Oh no, seems like something is not working.. We can't save your data.";
        }
        return response;
    }
}
