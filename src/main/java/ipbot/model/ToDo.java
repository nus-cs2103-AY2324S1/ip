package ipbot.model;

/**
 * Represents a todo, with a description.
 */
public class ToDo extends Task {
    /**
     * Defines a ToDo object with the given description.
     *
     * @param description The description of the todo.
     */
    public ToDo(String description){
        super(description);
    }

    @Override
    public String toCommaString() {
        String[] commaStringValues = {
                "T",
                this.isDone ? "X" : " ",
                this.description,
        };
        String commaString = String.join(",", commaStringValues);
        return commaString;
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
