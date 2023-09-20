package arona;

import java.util.Stack;

import arona.commands.Command;
import arona.parser.Parser;
import arona.storage.Storage;
import arona.task.TaskList;
import arona.ui.Ui;

/**
 * Arona is a simple command-line task management application that allows users to manage their tasks.
 * Users can add tasks, mark tasks as done, delete tasks, and list all tasks.
 */

public class Arona {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Stack<Command> commandHistory = new Stack<>();

    /**
     * Initializes a new instance of the Arona application.
     */
    public Arona() {
        ui = new Ui();
        storage = new Storage("./src/main/java/arona/data/arona.txt");
        tasks = new TaskList(storage);
    }

    /**
     * Generates a response to user input.
     *
     * @param input The user's input.
     * @return The response generated by Arona.
     */
    public String getResponse(String input) {

        String[] inputTokens = Parser.parseUserInput(input);
        String command = Parser.getCommand(inputTokens);
        Command cmd = Parser.parseCommand(command, inputTokens, tasks, ui, storage, commandHistory);
        commandHistory.push(cmd);
        return cmd.execute();
    }
}

