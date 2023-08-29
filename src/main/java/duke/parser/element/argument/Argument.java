package duke.parser.element.argument;

import duke.exception.DukeException;
import duke.parser.element.CommandElement;

public abstract class Argument implements CommandElement {

    private String text;

    public Argument(String text) {
        this.text = text;
    }

    @Override
    public String getName() {
        return String.format("{ %s }", this.text);
    }

    @Override
    public String getRegexForm() {
        return "(.*)";
    }
    
    @Override
    public String toString() {
        return this.text;
    }

    public abstract Object formatInput(String input) throws DukeException;
    public abstract String formatOutput(Object val);
    
}
