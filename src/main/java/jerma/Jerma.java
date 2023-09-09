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
        ui = new Ui();
        running = new Boolean[] { true };
    }

    public String load() {
        try {
            tasks = Storage.load();
            return "Task list has been loaded";
        } catch (IOException e) {
            return ui.error("Save file not found");
        } catch (UnsupportedOperationException e) {
            return ui.error("Corrupted save file");
        }
    }

    public String hello() {
        return ui.hello();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input, ui, tasks, running);
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
