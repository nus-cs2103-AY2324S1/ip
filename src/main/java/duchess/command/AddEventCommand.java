package duchess.command;

import duchess.DuchessException;
import duchess.Event;
import duchess.Parser;
import duchess.TaskList;
import duchess.Ui;

/**
 * Class representing an AddEvent command.
 */
public class AddEventCommand extends Command {
    /**
     * Adds a Event task.
     *
     * @param userInput - the user input.
     * @param tasks       the list of tasks.
     * @return            Duchess' response to the command.
     */
    @Override
    public String execute(String userInput, TaskList tasks) {
        String response = "";
        try {
            Event event = Parser.parseEventCommand(userInput);

            assert event != null : "Event is null";

            tasks.addTask(event);

            response += Ui.duchessFormat("Added successfully!! d(*⌒▽⌒*)b");
            response += Ui.duchessFormat(String.format("%d: %s", tasks.indexOf(event) + 1, event.toString()));
            response += Ui.duchessFormat("");
            response += Ui.duchessFormat(String.format("Now you have %d task(s)!! ヽ(´▽`)/", tasks.size()));

            return response;
        } catch (DuchessException e) {
            response += Ui.duchessFormat(e.getMessage());
            response += Ui.duchessFormat("(／°▽°)／Try something like this!!");
            response += Ui.duchessFormat("event [name] /from [start time] /to [end time]");
        }

        return response;
    }
}
