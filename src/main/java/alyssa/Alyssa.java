package alyssa;

import java.io.IOException;

import alyssa.exceptions.AlyssaArgumentException;
import javafx.fxml.FXML;
/**
 * This class represents the main program.
 */
public class Alyssa {
    private static final String FILEPATH = "./data/alyssa.txt";
    private static final String DIRPATH = "./data";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor method for Alyssa.
     */
    public Alyssa() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(FILEPATH, DIRPATH);
        this.taskList = new TaskList(storage.loadTasks());
    }
    private String handleGoodbye() {
        return ui.goodbye();
    }
    private String handleListing() {
        return taskList.listTasks();
    }
    private String handleMarkTask(String rest) {
        try {
            String output = taskList.markTask(rest);
            return output;
        } catch (NumberFormatException e) {
            return e.getMessage();
        } catch (AlyssaArgumentException e) {
            return e.getMessage();
        }
    }
    private String handleUnmarkTask(String rest) {
        try {
            String output = taskList.unmarkTask(rest);
            return output;
        } catch (NumberFormatException e) {
            return e.getMessage();
        } catch (AlyssaArgumentException e) {
            return e.getMessage();
        }
    }
    private String handleTodo(String rest) {
        try {
            String output = taskList.addTodo(rest);
            return output;
        } catch (AlyssaArgumentException e) {
            return e.getMessage();
        }
    }
    private String handleDeadline(String rest) {
        try {
            String output = taskList.addDeadline(rest);
            return output;
        } catch (AlyssaArgumentException e) {
            return e.getMessage();
        }
    }
    private String handleEvent(String rest) {
        try {
            String output = taskList.addEvent(rest);
            return output;
        } catch (AlyssaArgumentException e) {
            return e.getMessage();
        }
    }
    private String handleDelete(String rest) {
        try {
            String output = taskList.deleteTask(rest);
            return output;
        } catch (AlyssaArgumentException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return e.getMessage();
        }
    }
    private String handleFind(String rest) {
        return taskList.printRelevantTasks(rest);
    }
    private String handleInvalidResponse() {
        return ui.invalidTaskResponse();
    }
    private String run(Command command, String rest) {
        switch (command) {
        case BYE:
            return handleGoodbye();
        case LIST:
            return handleListing();
        case MARK:
            return handleMarkTask(rest);
        case UNMARK:
            return handleUnmarkTask(rest);
        case TODO:
            return handleTodo(rest);
        case DEADLINE:
            return handleDeadline(rest);
        case EVENT:
            return handleEvent(rest);
        case DELETE:
            return handleDelete(rest);
        case FIND:
            return handleFind(rest);
        default:
            return handleInvalidResponse();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    @FXML
    public String getResponse(String input) {
        String[] parsedInput = this.parser.parseCommand(input);
        String commandString = parsedInput[0];
        Command command;
        command = Command.assignCommand(commandString);
        String rest = parsedInput.length > 1 ? parsedInput[1] : "";
        String output = run(command, rest);
        assert output.length() > 0: "Program output cannot be empty";
        try {
            storage.saveTasks(taskList);
        } catch (IOException e) {
            return "Oops... We couldn't save your task data to a file :(";
        }
        return output;
    }
}
