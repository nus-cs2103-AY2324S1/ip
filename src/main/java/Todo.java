public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String displayTask() {
        return (".[T]" + super.getStatusIcon() + description);
    }

    @Override
    public void addedTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  [T]" + super.getStatusIcon() + description);
    }

    @Override
    public void displayTaskMark() {
        System.out.println("[T]" + super.getStatusIcon() + description);
    }
    
    public static Todo readFromFile(String[] segments) {
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
