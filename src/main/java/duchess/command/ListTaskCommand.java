package duchess.command;

import duchess.Task;
import duchess.TaskList;
import duchess.Ui;

import java.util.ArrayList;

/**
 * Class representing a ListTask command.
 */
public class ListTaskCommand extends Command {

    /**
     * Prints all stored Tasks.
     *
     * @param s - the string to be stored.
     * @return    Duchess' response to the command.
     */
    @Override
    public String execute(String userInput, TaskList tasks) {
        // An array of the segments of the response. This is used to prevent the issue where lambdas can only modify final variables.
        final ArrayList<String> responsesArray = new ArrayList<>();

        responsesArray.add(Ui.duchessFormat("Here are the things you said!! ヽ(^o^)丿"));
        tasks.forEach((Task t, Integer index) -> {
            responsesArray.add(Ui.duchessFormat(String.format("%d: %s", index + 1, tasks.getTask(index).toString())));
        });

        String response = "";
        for (String responsePart : responsesArray) {
            response += responsePart;
        }

        return response;
    }
}
