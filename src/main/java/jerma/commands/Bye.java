package jerma.commands;

import java.io.IOException;

import jerma.utils.Storage;
import jerma.utils.TaskList;
import jerma.utils.Ui;

/**
 * Bye class, is a Command that exits the program
 */
public class Bye extends Command {
    private Boolean[] running;

    /**
     * Creates a Bye command
     *
     * @param ui      Ui instance
     * @param tasks   Current tasklist
     * @param running Current bot state
     */
    public Bye(Ui ui, TaskList tasks, Boolean[] running) {
        super(ui, tasks);
        this.running = running;
    }

    @Override
    public String execute() {
        try {
            Storage.save(tasks);
        } catch (IOException e) {
            ui.error("Filepath issue");
        } catch (Exception e) {
            ui.error("Failed to save");
        }
        running[0] = false;
        return ui.bye();
    }
}
