package duke.parser.element.argument;

import duke.exception.InvalidIndexException;

/**
 * Input must be an index.
 */
public class IndexArgument extends Argument {

    /**
     * Constructor for IndexArgument.
     * 
     * @param text The name of the input field.
     */
    public IndexArgument(String text) {
        super(text);
    }

    /**
     * @inheritdoc
     */
    @Override
    public Integer formatInput(String input) throws InvalidIndexException {
        int idx;
        try {
            idx = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
        if (idx <= 0) {
            throw new InvalidIndexException();
        }
        return idx;
    }

    /**
     * @inheritdoc
     */
    @Override
    public String formatOutput(Object val) {
        return String.valueOf((int) ((Integer) val));
    }

}
