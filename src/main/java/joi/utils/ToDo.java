package joi.utils;

public class ToDo extends Task {
    public ToDo (String description) throws InvalidCommandException {
        super();
        if (description.length() <= 5) {
            throw new InvalidCommandException("The description of a todo cannot be empty.");
        }
        description = description.substring(5);

        this.description = description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
