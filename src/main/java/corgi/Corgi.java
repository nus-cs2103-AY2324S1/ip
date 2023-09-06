package corgi;

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

    /**
     * Constructs new Corgi chatbot with an empty task list.
     */
    public Corgi() {
        this.renderer = new TextRenderer();
        this.storage = new Storage<>(new TaskParser(), "./data/tasks.txt");
        this.tasks = new TaskList(storage.load());

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
        Command cmd = null;

        try {
            cmd = new CommandParser().parse(input);
            return cmd.execute(this.tasks, this.renderer, this.storage);
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
