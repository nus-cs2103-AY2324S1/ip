package LogicHandlers.CommandHandlers;

import Exceptions.DukeInvalidIndexException;
import Models.TaskArray;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MarkCommandHandler handles all mark commands.
 */
public class MarkCommandHandler implements Command{
    private TaskArray tasks;

    /**
     * Constructor for MarkCommandHandler.
     *
     * @param tasks The TaskArray we are working with
     */
    public MarkCommandHandler(TaskArray tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses the content of the input.
     *
     * @param commandContent The content of the input.
     */
    @Override
    public String parseCommandContent(String commandContent) {
        if (commandContent.equals("")) {
            return ("You cannot mark an empty task!");

        } else {
            int index;

            try {
                // Regex to tell if content is an integer
                Pattern intPattern = Pattern.compile("^\\d+$");
                Matcher matcher = intPattern.matcher(commandContent);

                if (!matcher.matches()) {
                    throw new DukeInvalidIndexException("Invalid task index! Please try again.");
                }

                index = Integer.parseInt(commandContent) - 1;

                if (index >= tasks.size() || index < 0) {
                    return ("Task " + (index + 1) + " not found!");
                } else {
                    tasks.markTask(index);

                    return ("Nice! I've marked this task as done: \n" +
                            tasks.get(index));
                }
            } catch (DukeInvalidIndexException e) {
                return (e.toString());
            }
        }
    }
}

