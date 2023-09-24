package duchess.command;

import duchess.DuchessException;
import duchess.Parser;
import duchess.Task;
import duchess.TaskList;
import duchess.Ui;

/**
 * Class representing a AddTag command.
 */
public class AddTagCommand extends Command {
    /**
     * Adds tags to a task.
     *
     * @param userInput - the user input.
     * @param tasks       the list of tasks.
     * @return            Duchess' response to the command.
     */
    @Override
    public String execute(String userInput, TaskList tasks) {
        String response = "";
        try {
            int tagIndex = Parser.parseAddTagCommandIndex(userInput);
            String[] tags = Parser.parseAddTagCommandTags(userInput);

            // Within bounds
            if (tagIndex < 0 || tagIndex >= tasks.size()) {
                response += Ui.duchessFormat("(´；ω；`) Sorry, no such task exists... ;-;");
                return response;
            }

            Task task = tasks.getTask(tagIndex);

            for (String tag : tags) {
                task.addTag(tag);
            }

            response += Ui.duchessFormat("Tags have been added!! (＾▽＾)");
            response += Ui.duchessFormat(String.format("%d: %s", tasks.indexOf(task) + 1, task.toString()));

            return response;

        } catch (DuchessException e) {
            response += Ui.duchessFormat(e.getMessage());
            response += Ui.duchessFormat("(／°▽°)／Try something like this!!");
            response += Ui.duchessFormat("tag add [task number] [tag name]");
        }
        return response;
    }
}
