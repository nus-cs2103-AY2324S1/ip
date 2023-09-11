package corgi;

import java.util.Stack;

import corgi.commands.Command;
import corgi.commands.CommandExecutionException;
import corgi.commands.CommandType;
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
    private TaskList tasks;
    private Storage<Task> storage;
    private TextRenderer renderer;
    private Stack<Pair<Command, TaskList>> history;

    /**
     * Constructs new Corgi chatbot with an empty task list, 
     * a text renderer, a storage and a history stack.
     */
    public Corgi() {
        this.renderer = new TextRenderer();
        this.storage = new Storage<>(new TaskParser(), "./data/tasks.txt");
        this.tasks = new TaskList(storage.load());
        this.history = new Stack<>();
        // if (tasks.size() > 0) {
        //     this.renderer.showTasksLoaded(tasks.size());
        // }
    }

    public String getIntro() {
        return renderer.showIntro();
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
            return cmd.execute(this.tasks, this.renderer, this.storage, this.history);
        } catch (InvalidCommandFormatException e) {
            return this.renderer.showError(e.getClass().getSimpleName(), e.getMessage());
        } catch (InvalidCommandTypeException e) {
            // Todo: Print all valid commands
            return this.renderer.showError(e.getClass().getSimpleName(), e.getMessage());
        } catch (CommandExecutionException e) {
            return this.renderer.showError(e.getClass().getSimpleName(), e.getMessage());
        }
    }
}
