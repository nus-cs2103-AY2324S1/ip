package duke.parser.element.argument;

import duke.exception.DukeException;
import duke.parser.element.CommandElement;

/**
 * The variable user inputs in a command.
 */
public abstract class Argument implements CommandElement {

    private String text;

    /**
     * Constructor for Argument.
     * 
     * @param text The name of the input field.
     */
    public Argument(String text) {
        this.text = text;
    }

    /**
     * @inheritdoc
     */
    @Override
    public String getName() {
        return String.format("{ %s }", this.text);
    }

    /**
     * @inheritdoc
     */
    @Override
    public String getRegexForm() {
        return "(.*)";
    }

    /**
     * @inheritdoc
     */
    @Override
    public String toString() {
        return this.text;
    }

    /**
     * Interprets the input.
     * 
     * @param input The user input.
     * @return The object interpreted with semantic meaning to the program.
     * @throws DukeException if the input cannot be converted into something of meaning.
     */
    public abstract Object formatInput(String input) throws DukeException;

    /**
     * Converts a value from the program to readable form.
     * 
     * @param val Object with semantic meaning to the program.
     * @return The readable form of the object.
     */
    public abstract String formatOutput(Object val);
    
}
