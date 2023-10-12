package duke.parser.element;

/**
 * A substring of a command representing a logical unit.
 */
public interface CommandElement {

    /**
     * Gets the name of the command element.
     *
     * @return Name of the command element.
     */
    abstract String getName();

    /**
     * Gets how the command element will be represented in regex.
     *
     * @return The command element in regex form.
     */
    abstract String getRegexForm();

}
