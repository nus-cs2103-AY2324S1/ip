package duke.commands;

import java.io.IOException;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents a command to exit the program
 */
public class ByeCommand extends Command {

    /**
     * Represents a constructor of the ByeCommand object
     */
    public ByeCommand() {
        super();
    }

    /**
     * Executes the ByeCommand and returns a string
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String output = "";
        this.isExit = true;
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
