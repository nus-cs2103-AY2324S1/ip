package corgi;

import java.util.Stack;

import corgi.commands.Command;
import corgi.commands.CommandExecutionException;
import corgi.parsers.CommandParser;
import corgi.parsers.InvalidCommandFormatException;
import corgi.parsers.InvalidCommandTypeException;
import corgi.parsers.TaskParser;
import corgi.storage.Storage;
import corgi.tasks.Task;
import corgi.tasks.TaskList;
import corgi.ui.TextRenderer;
import javafx.util.Pair;


/**
 * The `Corgi` class represents a chatbot named Corgi that manages tasks.
 * It provides a command-line interface for interacting with the chatbot.
 *
 * This class initializes the chatbot and handles user input and commands.
 */
public class Corgi {
    private State state;
    private Stack<Pair<State, Command>> history;

    /**
     * Constructs new Corgi chatbot with an empty task list,
     * a text renderer, a storage and a history stack.
     */
    public Corgi() {
        TextRenderer newRenderer = new TextRenderer();
        Storage<Task> newStorage = new Storage<>(new TaskParser(), "./data/tasks.txt");
        TaskList newList = new TaskList(newStorage.load());
        this.state = new State(newList, newStorage, newRenderer);
        this.history = new Stack<>();
    }

    public String getIntro() {
        return this.state.getTextRenderer().showIntro();
    }

    /**
     * Starts the chatbot - Corgi.
     */
    public String getResponse(String input) {
        assert !input.equals("") : "Input cannot be empty";

        Command cmd = null;

        try {
            cmd = new CommandParser().parse(input);
            assert cmd != null : "Command returned from parser cannot be null";
            Pair<State, String> result = cmd.execute(this.state, this.history);
            this.state = result.getKey();
            return result.getValue();
        } catch (InvalidCommandFormatException e) {
            return this.state.getTextRenderer().showError(e.getClass().getSimpleName(), e.getMessage());
        } catch (InvalidCommandTypeException e) {
            // Todo: Print all valid commands
            return this.state.getTextRenderer().showError(e.getClass().getSimpleName(), e.getMessage());
        } catch (CommandExecutionException e) {
            return this.state.getTextRenderer().showError(e.getClass().getSimpleName(), e.getMessage());
        }
    }
}
