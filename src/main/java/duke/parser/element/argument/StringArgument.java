package duke.parser.element.argument;

import duke.exception.EmptyArgException;

public class StringArgument extends Argument {

    public StringArgument(String text) {
        super(text);
    }

    @Override
    public String formatInput(String input) throws EmptyArgException {
        if (input == null || input.equals("")) {
            throw new EmptyArgException(this.toString());
        }
        return input;
    }

    @Override
    public String formatOutput(Object val) {
        return val.toString();
    }
    
}
