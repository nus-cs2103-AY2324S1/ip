package ipbot.model;

import java.time.Period;

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
                String.valueOf(TaskType.TODO.getType()),
                String.valueOf(this.getStatusIcon()),
                this.description,
        };
        String commaString = String.join(",", commaStringValues);
        return commaString;
    }

    @Override
    public String toString(){
        return "[" + TaskType.TODO.getType() + "]" + super.toString();
    }

    @Override
    public ToDo copy() {
        return new ToDo(this.description);
    }

    @Override
    public void translateTime(Period period) {
    }
}
