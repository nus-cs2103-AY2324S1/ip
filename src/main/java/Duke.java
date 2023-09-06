import duke.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;

/**
 * Main class for the duke application.
 */

public class Duke {
    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Image user = new Image(this.getClass().getResourceAsStream("images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("images/DaDuke.png"));

    public Duke() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage("./data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */

    public String getResponse(String input) {
        String command = parser.parseCommand(input);
        String response = "";
        GuiResponse guiResponse = new GuiResponse();
        if (command.equals("list")) {
            response = guiResponse.getTaskList(tasks);
        } else if (command.equals("mark")) {
            int index = parser.parseToIndex();
            Task curr = tasks.getTask(index);
            curr.markAsDone();
            storage.store(tasks);
            response = guiResponse.getMark(curr, index);
        } else if (command.equals("unmark")) {
            int index = parser.parseToIndex();
            Task curr = tasks.getTask(index);
            curr.markAsNotDone();
            storage.store(tasks);
            response = guiResponse.getUnmark(curr, index);
        } else if (command.equals("bye")) {
            response = guiResponse.getGoodbyeMessage();
        } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
            Task curr = parser.parseToTask();
            if (curr == null) {
                //need some handling here todo
                return "";
            }
            tasks.addTask(curr);
            storage.store(tasks);
            response = guiResponse.getAddTask(curr, tasks.getSize());
        } else if (command.equals("delete")) {
            int index = parser.parseToIndex();
            Task curr = tasks.getTask(index);
            tasks.deleteTask(index);
            storage.store(tasks);
            response = guiResponse.getDelete(curr, tasks.getSize());
        } else if (command.equals("find")) {
            String query = parser.parseQuery();
            response = guiResponse.getQueryResult(tasks.searchTask(query));
        } else {
            //nothing found
            response = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
        return response;
    }

    /**
     * Main driver code for duke class.
     */
    public void run() throws IOException {
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            //todo: replace the input here with gui input
            String str = bf.readLine();
            String command = parser.parseCommand(str);
            //regex detect all space
            if (command.equals("list")) {
                ui.printTaskList(tasks);
            } else if (command.equals("mark")) {
                int index = parser.parseToIndex();
                Task curr = tasks.getTask(index);
                curr.markAsDone();
                storage.store(tasks);
                ui.printMark(curr, index);
            } else if (command.equals("unmark")) {
                int index = parser.parseToIndex();
                Task curr = tasks.getTask(index);
                curr.markAsNotDone();
                storage.store(tasks);
                ui.printUnmark(curr, index);
            } else if (command.equals("bye")) {
                ui.printGoodbyeMessage();
                isExit = true;
            } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                Task curr = parser.parseToTask();
                if (curr == null) {
                    continue;
                }
                tasks.addTask(curr);
                storage.store(tasks);
                ui.printAddTask(curr, tasks.getSize());
            } else if (command.equals("delete")) {
                int index = parser.parseToIndex();
                Task curr = tasks.getTask(index);
                tasks.deleteTask(index);
                storage.store(tasks);
                ui.printDelete(curr, tasks.getSize());
            } else if (command.equals("find")) {
                String query = parser.parseQuery();
                ui.printQueryResult(tasks.searchTask(query));
            } else {
                //nothing found
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

    }
}
