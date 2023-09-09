package duke;

import command.Command;
import exception.DukeException;
import exception.EmptyDateTimeException;
import exception.EmptyInputException;
import exception.InvalidCommandException;
import exception.InvalidDateTimeException;
import exception.InvalidFormatException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 * Duke is the chatbot program.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * The constructor of Duke.
     *
     * @param filePath The file path to be passed into to load the initial tasks.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.load());
        this.parser = new Parser(this.taskList, this.ui, this.storage);

    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response;
        ui.readCommand(input);
        Command c = parser.parse(input);
        try {
            response = c.execute(taskList, ui, storage);
        } catch (EmptyInputException e) {
            response = e.getMessage();
        } catch (EmptyDateTimeException e) {
            response = e.getMessage();
        } catch (InvalidFormatException e) {
            response = e.getMessage();
        } catch (InvalidCommandException e) {
            response = e.getMessage();
        } catch (InvalidDateTimeException e) {
            response = e.getMessage();
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }

    /*
    public void run() {
        // ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            } catch (EmptyDateTimeException e) {
                System.out.println(e.getMessage());
            } catch (InvalidFormatException e) {
                System.out.println(e.getMessage());
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            } catch (InvalidDateTimeException e) {
                System.out.println(e.getMessage());
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("☹ OOPS!!! Something went wrong D:"
                        + Ui.SEPARATOR);
                e.printStackTrace();
            } finally {
                storage.writeTasks(taskList);
            }
        }
    }
    */
}

