package duke.parser.element.field;

import duke.parser.element.CommandElement;

/**
 * A fixed string that acts as a delimiter in a command.
 */
public class Field implements CommandElement {

    private String text;

    /**
     * Constructor for Field.
     * 
     * @param text The text represented by the field.
     */
    public Field(String text) {
        this.text = text;
    }

    /**
     * @inheritdoc
     */
    @Override
    public String getName() {
        return this.text;
    }

    /**
     * @inheritdoc
     */
    @Override
    public String getRegexForm() {
        return String.format("( %s )", this.text);
    }

    /**
     * @inheritdoc
     */
    @Override
    public String toString() {
        return this.text;
    }
    
}
