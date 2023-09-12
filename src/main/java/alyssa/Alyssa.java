package alyssa;

import java.io.IOException;

import alyssa.Exceptions.AlyssaArgumentException;

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

    /*
     * Constructor method for Alyssa.
     */
    public Alyssa() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(FILEPATH, DIRPATH);
        this.taskList = new TaskList(storage.loadTasks());
    }

    private String run(Command command, String rest) {
        switch (command) {
        case BYE:
            return ui.goodbye();
        case LIST:
            return taskList.listTasks();
        case MARK:
            try {
                String output = taskList.markTask(rest);
                return output;
            } catch (NumberFormatException e) {
                return e.getMessage();
            } catch (AlyssaArgumentException e) {
                return e.getMessage();
            }
        case UNMARK:
            try {
                String output = taskList.unmarkTask(rest);
                return output;
            } catch (NumberFormatException e) {
                return e.getMessage();
            } catch (AlyssaArgumentException e) {
                return e.getMessage();
            }
        case TODO:
            try {
                String output = taskList.addTodo(rest);
                return output;
            } catch (AlyssaArgumentException e) {
                return e.getMessage();
            }
        case DEADLINE:
            try {
                String output = taskList.addDeadline(rest);
                return output;
            } catch (AlyssaArgumentException e) {
                return e.getMessage();
            }
        case EVENT:
            try {
                String output = taskList.addEvent(rest);
                return output;
            } catch (AlyssaArgumentException e) {
                return e.getMessage();
            }
        case DELETE:
            try {
                String output = taskList.deleteTask(rest);
                return output;
            } catch (AlyssaArgumentException e) {
                return e.getMessage();
            } catch (NumberFormatException e) {
                return e.getMessage();
            }
        case FIND:
            return taskList.printRelevantTasks(rest);
        default:
            return ui.invalidTaskResponse();
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
