package duke.commands;

import java.io.IOException;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

public class ByeCommand extends Command {

    /**
     * Constructor of the ByeCommand object
     */
    public ByeCommand() {
        super();
    }

    /**
     * Execute the ByeCommand and returns a string
     * 
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String output = "";
        this.isExit = true;
        try {
            storage.save(tasks);
        } catch (IOException e) {
            output = "OOPS!!! There is no file to save.";
            System.out.println("OOPS!!! There is no file to save.");
        }
        output += ui.bye();
        return output;
    }
}
