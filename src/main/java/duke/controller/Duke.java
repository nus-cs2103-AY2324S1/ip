package duke.controller;

import duke.command.Command;
import duke.exception.DukeException;
import duke.object.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;

/**
 * The main chatbot.
 */
public class Duke {

    private String name;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs Duke.
     *
     * @param name Name of the bot.
     */
    public Duke(String name) {
        this.name = name;
        this.storage = new Storage();
        this.tasks = this.storage.loadTasks();
    }

    /**
     * Returns default greeting message.
     *
     * @return Default greeting message.
     */
    public String greet() {
        return String.format("Hello! I'm %s\nWhat can I do for you?", name);
    }

    /**
     * Generates response to command.
     *
     * @param input Command from user.
     * @return Response by chatbot.
     */
    public String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input);
            return cmd.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Saves tasks to file.
     */
    public void save() {
        storage.save(tasks);
    }

}
