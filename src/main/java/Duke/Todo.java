package Duke;

public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon() + description;
    }
    
    public static Todo readFromFile(String[] segments) throws GmanException {
        String symbol = segments[1];
        String description = segments[2];
        Todo toReturn =  new Todo(description);
        if (symbol.equals("X")) {
            toReturn.mark();
        }
        return toReturn;
    }


    @Override
    public String toWriteString() {
        String toWrite = "T" + " | " + super.toWriteString();
        return toWrite;
    }
}
