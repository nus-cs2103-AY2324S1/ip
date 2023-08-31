package duke.parser.element.argument;

import duke.exception.EmptyArgException;

/**
 * Input must be a string.
 */
public class StringArgument extends Argument {

    /**
     * Constructor for StringArgument.
     * 
     * @param text The name of the input field.
     */
    public StringArgument(String text) {
        super(text);
    }

    /**
     * @inheritdoc
     */
    @Override
    public String formatInput(String input) throws EmptyArgException {
        if (input == null || input.equals("")) {
            throw new EmptyArgException(this.toString());
        }
        return input;
    }

    /**
     * @inheritdoc
     */
    @Override
    public String formatOutput(Object val) {
        return val.toString();
    }
    
}
