package parser;

import commands.Commands;
import tasks.*;
import ui.Ui;
import main.Duke;
import storage.Storage;
import actions.TaskList;


import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {

    private Duke chad;
    private Ui ui;
    private Storage storage;
    private TaskList tasklist;
    private Commands commands;

    public Parser(Duke chad, ArrayList<Task> taskArrayList) {
        this.chad = chad;
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasklist = new TaskList(taskArrayList);
        this.commands = new Commands(chad, taskArrayList);
    }

    /**
     * Takes in a String array of the instructions from user
     * Runs the applicable methods based on the instruction from user
     * For Main Class (GUI)
     *
     * @param inputArray array of string from the input of user
     * @return false if user gives command to stop bot, else returns true
     */
    public String displayInputParse(String[] inputArray) {

        String command = inputArray[0];

        switch (command) {

        case "bye":
            return commands.byeCommand();

        case "list":
            return commands.listCommand();

        case "mark":
            return commands.markCommand(inputArray);

        case "unmark":
            return commands.unmarkCommand(inputArray);

        case "find":
            return commands.findCommand(inputArray);

        case "todo":
            return commands.todoCommand(inputArray);

        case "deadline":
            return commands.deadlineCommand(inputArray);

        case "event":
            return commands.eventCommand(inputArray);

        case "delete":
            return commands.deleteCommand(inputArray);

        case "edit":
            return commands.editCommand(inputArray);

        default:
            return ui.displayChadOutput("Hmm? You are not making sense!");
        }
    }
}




