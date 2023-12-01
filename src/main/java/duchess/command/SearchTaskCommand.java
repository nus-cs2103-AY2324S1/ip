package duchess.command;

import java.util.ArrayList;

import duchess.DuchessException;
import duchess.Parser;
import duchess.Task;
import duchess.TaskList;
import duchess.Ui;

/**
 * Class representing a SearchTask command.
 */
public class SearchTaskCommand extends Command {
    /**
     * Searches for all tasks by a specified string and returns the results.
     *
     * @param userInput - the user input.
     * @param tasks       the list of tasks.
     * @return            Duchess' response to the command.
     */
    @Override
    public String execute(String userInput, TaskList tasks) {
        String response = "";

        try {
            String searchQuery = Parser.parseSearchTaskCommand(userInput);

            // Stores the parts of the responses; workaround for not being able to reassign the constant
            // declared outside the lambda for the folowing filter operation
            final ArrayList<String> responsesArray = new ArrayList<>();

            assert searchQuery != null : "Search query is null";

            TaskList newTaskList = tasks.filterReplaceNull((Task t) -> t.getName().contains(searchQuery));

            responsesArray.add(Ui.duchessFormat("Here are the things I found!! ヽ(^o^)丿"));

            newTaskList.forEach((Task t, Integer index) -> {
                if (t != null) {
                    responsesArray.add(Ui.duchessFormat(String.format("%d: %s", index + 1, t.toString())));
                }
            });

            for (String responsePart : responsesArray) {
                response += responsePart;
            }

            return response;
        } catch (DuchessException e) {
            response += Ui.duchessFormat(e.getMessage());
            response += Ui.duchessFormat("(／°▽°)／Try something like this!!");
            response += Ui.duchessFormat("search [query]");
        }
        return response;
    }
}
