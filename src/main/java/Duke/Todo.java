package duke;

/**
 * A Todo Task that contains a description.
 */
public class Todo extends Task{

    /**
     * A Todo task constructor.
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * A string representation of the Todo Task containing the symbol, status icon and description.
     * @return A string to be printed to the user.
     */
    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon() + description;
    }

    /**
     * Reads a line in a .txt file that represents a Todo task and converts it to a Todo Task.
     * @param segments A string array that is split by " | ", separating the line into useful chunks.
     * @return A deadline task.
     */
    public static Todo readFromFile(String[] segments) {
        String symbol = segments[1];
        String description = segments[2];
        Todo toReturn =  new Todo(description);
        if (symbol.equals("X")) {
            toReturn.mark();
        }
        return toReturn;
        
    }


    /**
     * returns A String to be written into the .txt file.
     * @return A formatted String for the Todo task and " | " separators.
     */
    @Override
    public String toWriteString() {
        String toWrite = "T" + " | " + super.toWriteString();
        return toWrite;
    }
}
