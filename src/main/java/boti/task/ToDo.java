package boti.task;

import java.io.IOException;

import boti.exception.InvalidToDoException;


/**
 * Class for ToDo
 */
public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    };

    /**
     * Creates a new ToDo based on the message
     *
     * @param message the message to create the new todo
     * @return the new todo
     * @throws InvalidToDoException when the todo command message is invalid
     */
    public static ToDo create(String message) throws InvalidToDoException {
        assert message.split(" ")[0].equalsIgnoreCase("todo") : "First word of message must be todo";
        try {
            return new ToDo(message.substring(5));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidToDoException();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Creates new todo based on the string stored in the storage
     *
     * @param stored the string stored in the storage
     * @return the todo based on the string stored in the storage
     * @throws IOException when the string stored does not belong to todo
     */
    public static ToDo createFromStorage(String stored) throws IOException {
        assert stored.split(" \\| ")[0].equals("T") : "The first part of the string stored is T";
        String[] splitTaskInString = stored.split(" \\| ");
        String mark = splitTaskInString[1];
        String description = splitTaskInString[2];
        ToDo todo = new ToDo(description);
        if (mark.equals("1")) {
            todo.mark();
        }
        return todo;
    }
    @Override
    public String storeInString() {
        return "T | " + (this.getMark() ? "1 | " : "0 | ") + this.getName();
    }
}
