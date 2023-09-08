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

    /**
     * The constructor of Duke.
     *
     * @param filePath The file path to be passed into to load the initial tasks.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.load());
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Paimon heard: " + input;
    }

    /**
     * The method to run the chatbot program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                Parser parser = new Parser(this.taskList, this.ui);
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                c.execute(taskList, ui);
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
}

