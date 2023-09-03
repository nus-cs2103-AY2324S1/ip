package jerma;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import jerma.commands.Command;
import jerma.utils.Parser;
import jerma.utils.Storage;
import jerma.utils.TaskList;
import jerma.utils.Ui;

public class Jerma {
    private TaskList tasks;
    private Ui ui;
    private Boolean[] running;

    public Jerma() {
        this.ui = new Ui();
        this.running = new Boolean[] { true };

        try {
            this.tasks = Storage.load();
        } catch (IOException e) {
            ui.error("Save file not found");
        } catch (UnsupportedOperationException e) {
            ui.error("Corrupted save file");
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input, this.ui, this.tasks,
                    this.running);
            return command.execute();
        } catch (IndexOutOfBoundsException e) {
            return ui.error("Invalid arguments. Try again!");
        } catch (UnsupportedOperationException e) {
            return ui.error("Invalid command. Try again!");
        } catch (DateTimeParseException e) {
            return ui.error("Invalid date format. Try again!");
        }
    }
}
