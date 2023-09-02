package duke.commands;

import java.util.regex.Matcher;

import duke.exceptions.MissingDescriptionException;

/**
 * Represents a command that adds a task to the task list.
 */
public abstract class TaskCommand implements Command {

    /**
     * The matcher object used to extract the description.
     */
    protected Matcher matcher;

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * Creates a TaskCommand object.
     *
     * @param matcher The matcher object used to extract the description.
     * @throws MissingDescriptionException If the description is missing.
     */
    public TaskCommand(Matcher matcher) throws MissingDescriptionException {
        this.matcher = matcher;
        this.description = extractTaskDescription();
    }

    /**
     * The following methods are used to extract the description for the task
     * commands.
     *
     * @param input the raw input string
     * @return the corresponding tasks description, or null if the input is
     *         invalid
     * @throws MissingDescriptionException
     */
    private String extractTaskDescription() throws MissingDescriptionException {
        if (matcher.matches()) {
            String description = matcher.group("description"); // Store the description once a match is found
            if (description == null || description.trim().isEmpty()) {
                throw new MissingDescriptionException(matcher.group("command"));
            }
            return description.trim();
        }
        return null;
    }
}
