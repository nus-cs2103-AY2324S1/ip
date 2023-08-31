package jerma.commands;

import java.io.IOException;

import jerma.utils.Storage;
import jerma.utils.TaskList;
import jerma.utils.Ui;

public class Bye extends Command {
    private Boolean[] running;

    public Bye(Ui ui, TaskList tasks, Boolean[] running) {
        super(ui, tasks);
        this.running = running;
    }

    public void execute() {
        try {
            Storage.save(tasks);
        } catch (IOException e) {
            this.ui.error("Filepath issue");
        } catch (Exception e) {
            this.ui.error("Failed to save");
        }
        this.running[0] = false;
        this.ui.bye();
    }
}
