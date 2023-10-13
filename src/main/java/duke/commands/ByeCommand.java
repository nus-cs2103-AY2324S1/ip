package duke.commands;

import java.io.IOException;

import duke.Storage;
import duke.Ui;
import duke.exception.UnknownCommandException;
import duke.task.TaskList;

/**
 * Represents a command to exit the program
 */
public class ByeCommand extends Command {
    private String input;

    /**
     * Represents a constructor of the ByeCommand object
     */
    public ByeCommand(String input) {
        super();
        this.input = input;
    }

    /**
     * Executes the ByeCommand and returns a string
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, UnknownCommandException {
        String output = "";
        this.isExit = true;
        String[] split = input.split(" ");
        if (split.length > 1) {
            throw new UnknownCommandException("I am sorry, I do not get what you mean.");
        }
        try {
            storage.save(tasks);
        } catch (IOException e) {
            output = "PIKA PIKA!!! There is no file to save.";
            System.out.println("PIKA PIKA!!! There is no file to save.");
        }
        output += ui.bye();
        return output;
    }
}
