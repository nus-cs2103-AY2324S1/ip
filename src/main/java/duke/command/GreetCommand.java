package duke.command;

import java.util.stream.Stream;

import duke.core.Storage;
import duke.core.Ui;
import duke.task.TaskList;

/**
 * Command to greet the user.
 */
public class GreetCommand extends Command {
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.respond(Stream.of("Hello! I'm A-CAT (Automated Chatbot Assistant for Tasks)", "What do you want to do today?"));
    }
}
